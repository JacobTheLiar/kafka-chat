import {Component, inject, input, model, OnInit, signal} from '@angular/core';
import {ChatMessage} from '../../model/chat-message.model';
import {ChatWebSocket} from '../../service/web-socket.service';
import {FormsModule} from '@angular/forms';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-chat-room',
  imports: [
    FormsModule,
    DatePipe
  ],
  templateUrl: './chat-room.html',
  styleUrl: './chat-room.scss',
})
export class ChatRoom implements OnInit{

  protected readonly username = input.required<string>();
  protected readonly roomName = input.required<string>();

  private readonly webSocketService = inject(ChatWebSocket);

  protected messages = signal<ChatMessage[]>([]);
  protected currentMessage = model('');

  ngOnInit() {
    this.webSocketService.messages$.subscribe(message => {
      this.messages.update(messages => [...messages, message]);
    });
  }

  onSendMessageClickEvent(){
    const message = this.currentMessage().trim();

    if (!message) {
      return;
    }

    this.webSocketService.sendMessage(this.username(), message);
    this.currentMessage.set('');
  }

}
