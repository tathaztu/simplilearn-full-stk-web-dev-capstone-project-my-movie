import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  loggedInUser?:User;
  constructor(private httpClient:HttpClient) { }

  authenticateUser(userId: string, pwd: string) {

    let httpParams = new HttpParams().append("userId", userId).append("pwd", pwd);
    
    return this.httpClient.post<User>(
        'http://localhost:8080/user/authenticate', 
        null, 
        {
          params: httpParams 
        }
    );
  }

  registerUser(user: User) {
    return this.httpClient.post<User>('http://localhost:8080/user/save', user);
  }
}
