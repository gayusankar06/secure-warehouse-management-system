import { Component } from '@angular/core';

import { FormsModule } from '@angular/forms';

import {
  Router,
  RouterLink
} from '@angular/router';

import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  username = '';
  password = '';
  role = 'BUYER';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register() {
    const user = {
      username: this.username,
      password: this.password,
      role: this.role
    };

    this.authService.register(user).subscribe({
      next: () => {
        alert('Registration successful. Please login.');
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error(error);
        alert('Registration failed. Please try again.');
      }
    });
  }

}
