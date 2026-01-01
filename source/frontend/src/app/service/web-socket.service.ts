import {Injectable, signal} from '@angular/core';
import {Client} from '@stomp/stompjs';
import {Subject} from 'rxjs';
import {ChatMessage} from '../model/chat-message.model';
import {RoomData} from '../model/room-data.model';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root',
})
export class ChatWebSocket {

  private stompClient?: Client

  private messagesSubject = new Subject<ChatMessage>();
  public messages$ = this.messagesSubject.asObservable();

  public isConnected = signal<boolean>(false);
  public currentRoom = signal<string | null>(null);


  connect(roomData: RoomData){
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),

      onConnect: () => {
        if (this.stompClient) {
          const { roomName } = roomData;
          this.stompClient.subscribe(`/topic/room/${roomName}`, (message) => {
            const chatMessage: ChatMessage = JSON.parse(message.body);
            this.messagesSubject.next(chatMessage); // emit do Observable
          });

          this.isConnected.set(true);
          this.currentRoom.set(roomName);
        }
      },

      onStompError: (frame) => {
        console.error('STOMP Error:', frame);
      },

      onWebSocketError: (error) => {
        console.error('WebSocket Error:', error);
      },

    });

    this.stompClient.activate();
  }

  sendMessage(username: string, message: string): void {
    if (!this.stompClient?.connected || !this.currentRoom()) {
      console.error('Not connected to WebSocket');
      return;
    }

    const payload = {
      roomName: this.currentRoom(),
      username: username,
      message: message
    };

    this.stompClient.publish({
      destination: '/app/chat.send',
      body: JSON.stringify(payload)
    });
  }

  disconnect(): void {
    if (this.stompClient) {
      this.stompClient.deactivate()
        .then(() => this.currentRoom.set(null))
        .finally(() => this.isConnected.set(false));
    }
  }
}
