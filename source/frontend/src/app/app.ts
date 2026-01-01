import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {JoinForm} from './components/join-form/join-form';
import {ChatRoom} from './components/chat-room/chat-room';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, JoinForm, ChatRoom],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {

}
