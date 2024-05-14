import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import io, { Socket } from 'socket.io-client';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  socket: Socket;
  readonly url: string = environment.socketUrl;
  constructor() {
    this.socket = io(environment.socketUrl, { transports: ['websocket', 'polling', 'flashsocket'] });
  }


  listen(eventName: string): Observable<any> {
    return new Observable((subscriber) => {
      this.socket.on(eventName, (data) => {
        subscriber.next(data);
      });

      // Hata durumunda
      this.socket.on('error', (error) => {
        console.error('Socket error:', error);
        subscriber.error(error); // Hata gönder
        subscriber.complete(); // Observable'ı tamamla
        this.disconnect(); // Bağlantıyı kapat
      });

      // Bağlantı kesildiğinde
      this.socket.on('disconnect', () => {
        console.log('Socket disconnected');
        subscriber.complete(); // Observable'ı tamamla
      });

      // Abone iptal edildiğinde
      return () => {
        this.disconnect(); // Bağlantıyı kapat
      };
    });
  }

  emit(eventName: string, data: any) {
    this.socket.emit(eventName, data);
  }

  disconnect() {
    this.socket.disconnect();
  }

}
