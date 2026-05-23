import { Injectable } from '@angular/core';

import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class ProductService {

  private apiUrl =
    'http://localhost:8081/api/products';

  constructor(private http: HttpClient) { }

  // CREATE HEADERS
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

  // GET PRODUCTS
  getProducts() {

    return this.http.get(

      this.apiUrl,

      this.getHeaders()

    );

  }

  // ADD PRODUCT
  addProduct(product:any) {
    return this.http.post(this.apiUrl, product, this.getHeaders());
  }

  // UPDATE PRODUCT
  updateProduct(id: number, product:any) {
    return this.http.put(`${this.apiUrl}/${id}`, product, this.getHeaders());
  }

  // DELETE PRODUCT
  deleteProduct(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`, this.getHeaders());
  }
}