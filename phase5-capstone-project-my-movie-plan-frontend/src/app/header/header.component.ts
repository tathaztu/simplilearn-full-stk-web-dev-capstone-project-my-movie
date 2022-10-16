import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { MsgDialogComponent } from '../msg-dialog/msg-dialog.component';
import { TicketsService } from '../services/tickets.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog,
    public userService:UserService,
    public ticketService:TicketsService
  ) {}

  ngOnInit(): void {}

  getContactInfo(): void {
    const dialogRef = 
      this.dialog.open(MsgDialogComponent, {
        data: {
          strMsgType: 'Info',
          strMsg: 'Contact us at student@simplilearn-cohort-jan22.com'
        },
      });
  }

  navigate2Login(): void {
    this.router.navigate(['login'])
  }

  getFirstLetter(){
    return this.userService.loggedInUser!.strFirstName.charAt(0);
  }

  logout(){
    console.log(this.userService.loggedInUser);
    this.userService.loggedInUser = undefined;
    this.ticketService.clearSelections();
    console.log(this.userService.loggedInUser);
  }

  navigate2MovieList(){
    this.router.navigate(['movie-list'])
  }

  navigate2Cart() {
    this.router.navigate(['checkout'])
  }

}