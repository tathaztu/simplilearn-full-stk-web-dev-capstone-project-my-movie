import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';
  errorMessage = '';
  invalidLogin = false;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    console.log("In LoginComponent's Constructor")
  }

  ngOnInit(): void {
  }

  handleLogin(){
    this.userService.authenticateUser(this.username, this.password).subscribe(
      data => {
        console.log(data);
        if (null != data) {
          this.router.navigate([''])
          this.invalidLogin = false;
          this.errorMessage = '';
          this.userService.loggedInUser = data;
        } else {
          this.invalidLogin = true;
          this.errorMessage = 'Worng Credentials'
        }
      }
    );
  }
  
  navigate2Register(){
    this.router.navigate(['register'])
  }
  
}
