import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgNumericKeyboardModule } from 'ng-numeric-keyboard';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgNumericKeyboardModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
