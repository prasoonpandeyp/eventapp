import { Component, OnInit } from '@angular/core';
import { EventserviceService } from '../eventservice.service';
import { EventModel } from '../models/eventmodel';

@Component({
  selector: 'app-whishlist',
  templateUrl: './whishlist.component.html',
  styleUrls: ['./whishlist.component.scss']
})
export class WhishlistComponent implements OnInit {

  //create list of events to display in component named wishlist
  wishlist: EventModel[] = [];

  //create list of events from eventmodel
  events: EventModel[] = [];
    //  constructor with eventservice as parameter
    constructor(private eventservice: EventserviceService) { }

  ngOnInit(): void {
    this.eventservice.getAllEvents().subscribe((data: any) => {
      this.events = data;
    });
  }

  addToCart(event: any) {
    console.log(event);
    this.wishlist.push(event); 
  }
  removeEvt(event: any) {
    console.log(event);
    this.wishlist = this.wishlist.filter((e: any) => e.name !== event.name);
  }
}
