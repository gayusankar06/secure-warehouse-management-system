import { Injectable } from '@angular/core';

import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class OrderService {

  private apiUrl =
    'http://localhost:8081/api/orders';

  constructor(private http: HttpClient) { }

  getHeaders() {

    const token =
      localStorage.getItem('token');

    return {

      headers: new HttpHeaders({

        Authorization:
          `Bearer ${token}`

      })

    };

  }

  getOrders() {

    return this.http.get(
      this.apiUrl,
      this.getHeaders()
    );

  }

  addOrder(order:any) {

    return this.http.post(
      this.apiUrl,
      order,
      this.getHeaders()
    );

  }

}