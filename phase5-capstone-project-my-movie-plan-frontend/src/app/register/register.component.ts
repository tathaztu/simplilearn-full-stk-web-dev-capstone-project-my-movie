import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../classes/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:User;
  error = '';

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    this.user = new User();
  }

  ngOnInit(): void {
  }

  handleRegistration(){
    this.userService.registerUser(this.user).subscribe(
      data => {
        this.user = data;
        console.log('Printing User after Save >>>> ' + JSON.stringify(this.user));
        this.router.navigate(['']);
      }
    );
  }
}
