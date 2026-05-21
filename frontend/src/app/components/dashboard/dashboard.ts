import { Component } from '@angular/core';

import {
  Router,
  RouterLink
} from '@angular/router';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',

  standalone: true,

  imports: [
    RouterLink,
    CommonModule
  ],

  templateUrl: './dashboard.html',

  styleUrl: './dashboard.css'
})

export class Dashboard {

  role = '';

  constructor(
    private router: Router
  ) {

    // GET ROLE FROM LOCAL STORAGE
    this.role =
      localStorage.getItem('role') || '';

  }

  logout() {

    // REMOVE TOKEN
    localStorage.removeItem('token');

    // REMOVE ROLE
    localStorage.removeItem('role');

    alert('Logged Out');

    // REDIRECT LOGIN
    this.router.navigate(['/']);

  }

}