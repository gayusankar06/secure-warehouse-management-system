import { Injectable } from '@angular/core';

import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class TrackingService {

  private apiUrl = 'http://localhost:8081/api/tracking';

  constructor(private http: HttpClient) { }

  getHeaders() {
    const token = localStorage.getItem('token');
    return {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      })
    };
  }

  getTrackingByOrder(orderId: number) {
    return this.http.get(`${this.apiUrl}/${orderId}`, this.getHeaders());
  }

  addTracking(tracking:any) {
    return this.http.post(this.apiUrl, tracking, this.getHeaders());
  }
}