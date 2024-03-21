import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppRoutingModule } from '../app-routing.module';
import { EventserviceService } from '../eventservice.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private router: ActivatedRoute, private rout: Router, private eventService: EventserviceService) { }

  ngOnInit(): void {
  }

  login() {
    console.log('login');
    // call login method of eventService
    this.eventService.login('username', 'password').subscribe(data => {
      console.log(data);
      if (data != null && data.getResp() != null && data.getResp() != undefined) {
        this.rout.navigate(['whishlist']);
      } else {
        this.rout.navigate(['login']);
      }
    });
  }

  register() {
    this.rout.navigate(['register']);

  }
}
