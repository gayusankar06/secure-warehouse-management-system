import { Injectable } from '@angular/core';

import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class TrackingService {

  private apiUrl =
    'http://localhost:8081/api/shipments';

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

  getShipments() {

    return this.http.get(
      this.apiUrl,
      this.getHeaders()
    );

  }

  addShipment(shipment:any) {

    return this.http.post(
      this.apiUrl,
      shipment,
      this.getHeaders()
    );

  }

}