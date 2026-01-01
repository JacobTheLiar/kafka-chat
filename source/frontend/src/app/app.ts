import {Component, inject, signal} from '@angular/core';
import {JoinForm} from './components/join-form/join-form';
import {ChatRoom} from './components/chat-room/chat-room';
import {RoomData} from './model/room-data.model';
import {ChatWebSocket} from './service/web-socket.service';

@Component({
  selector: 'app-root',
  imports: [ JoinForm, ChatRoom],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {

  protected readonly isJoined = signal(false);
  protected roomData = signal<RoomData | null>(null);

  private webSocketService = inject(ChatWebSocket);

  handleJoin(data: RoomData){
    this.roomData.set(data);
    this.webSocketService.connect(data);
    this.isJoined.set(true);
  }
}
