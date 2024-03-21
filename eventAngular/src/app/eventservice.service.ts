import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventModel } from './models/eventmodel';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class EventserviceService {

  // string for baseurl
  baseUrl: string = 'http://127.0.0.1:8090/api/event/v1';
  constructor(private http: HttpClient) { }

  // function to get all events
  getAllEvents(): Observable<EventModel[]> {
    return this.http.get<EventModel[]>(this.baseUrl + '/all-events');
  }
  // get events by userId
  getEventsByUserId(userId: number): Observable<EventModel[]>{
    return this.http.get<EventModel[]>(this.baseUrl + '/user/getEvents/' + userId.toString());
  }
  //add events to user's wishlist
  addEventToWishlist(userId: number, events: EventModel[]) {
    this.http.post(this.baseUrl + '/user/' + userId + '/updateEvents/' + userId, events).subscribe();
  }
  //create a method for login
  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.baseUrl + '/login', { username: username, password: password });
  }
  // add method for signup
  signUp(user: User): Observable<any> {
    return this.http.post<any>(this.baseUrl + '/user-management/register', user);
  }
}
