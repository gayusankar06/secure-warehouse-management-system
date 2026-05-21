import { Injectable } from '@angular/core';

import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class WarehouseService {

  private apiUrl =
    'http://localhost:8081/api/warehouses';

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

  getWarehouses() {

    return this.http.get(
      this.apiUrl,
      this.getHeaders()
    );

  }

  addWarehouse(warehouse:any) {

    return this.http.post(
      this.apiUrl,
      warehouse,
      this.getHeaders()
    );

  }

}