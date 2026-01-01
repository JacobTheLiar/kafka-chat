import {Component, model, output} from '@angular/core';
import {RoomData} from '../../model/room-data.model';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-join-form',
  templateUrl: './join-form.html',
  styleUrl: './join-form.scss',
  imports: [
    FormsModule
  ]
})
export class JoinForm {
  protected joined = output<RoomData>();
  protected username = model('');
  protected roomName = model('');

  onJoinClickEvent(): void{
    const username = this.username().trim();
    const roomName = this.roomName().trim();

    if (!username || !roomName) {
      return;
    }

    this.joined.emit({ username, roomName });
  }
}
