import { Component } from '@angular/core';

import { FormsModule } from '@angular/forms';

import {
  Router,
  RouterLink
} from '@angular/router';

import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',

  standalone: true,

  imports: [FormsModule, RouterLink],

  templateUrl: './login.html',

  styleUrl: './login.css'
})

export class Login {

  username = '';

  password = '';

  constructor(

    private authService: AuthService,

    private router: Router

  ) {}

  // DECODE JWT TOKEN
  decodeToken(token:string) {

    return JSON.parse(

      atob(token.split('.')[1])

    );

  }

  // LOGIN FUNCTION
  login() {

    const user = {

      username: this.username,

      password: this.password

    };

    this.authService

      .login(user)

      .subscribe({

        next: (response:any) => {

          if (!response || response.includes('Invalid')) {
            alert('Invalid login credentials');
            return;
          }

          // STORE JWT TOKEN
          localStorage.setItem(

            'token',

            response

          );

          // DECODE TOKEN
          const decoded =

            this.decodeToken(response);

          // STORE ROLE
          localStorage.setItem(

            'role',

            decoded.role || ''

          );

          alert('Login Successful');

          // NAVIGATE DASHBOARD
          this.router.navigate(

            ['/dashboard']

          );

        },

        error: (error) => {

          alert('Invalid Login');

          console.error(error);

        }

      });

  }

}