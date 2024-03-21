import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { WhishlistComponent } from './whishlist/whishlist.component';
import { EventserviceService } from './eventservice.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    WhishlistComponent
  ],
  imports: [
    HttpClientModule, // Add HttpClientModule to imports
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  //add provider eventService
  
  providers: [
    EventserviceService // Add EventService to providers
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
