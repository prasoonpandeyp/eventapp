import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { EventserviceService } from '../eventservice.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  signUpModel: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    mobile: new FormControl('', Validators.required)
    });
  
  // create a variable to store the user's data
  user: any = {
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    mobile: ''
  };
  
  // add parameter of event service as private member

  constructor(private formBuilder: FormBuilder, private router: Router, private eventservice: EventserviceService) { }
  
  ngOnInit(): void {
    this.signUpModel = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', Validators.required],
      mobile: ['', Validators.required]
    });
  }

  clear() {
    this.signUpModel?.reset();
  }
  signUp() {
    this.eventservice.signUp(this.signUpModel.value).subscribe((data: any) => {
      this.router.navigate(['login']);
    });
  }
}
