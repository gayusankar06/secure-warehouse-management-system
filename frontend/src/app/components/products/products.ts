import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { ProductService }
from '../../services/product';

@Component({
  selector: 'app-products',

  standalone: true,

  imports: [
    CommonModule,
    FormsModule
  ],

  templateUrl: './products.html',

  styleUrl: './products.css'
})

export class Products implements OnInit {

  products:any[] = [];

  name = '';

  category = '';

  price = 0;

  quantity = 0;

  role = '';

  constructor(
    private productService: ProductService
  ) {

    // GET USER ROLE
    this.role =
      localStorage.getItem('role') || '';

  }

  ngOnInit(): void {

    this.loadProducts();

  }

  // LOAD PRODUCTS
  loadProducts() {

    this.productService
      .getProducts()
      .subscribe((data:any) => {

        this.products = data;

      });

  }

  // ADD PRODUCT
  addProduct() {

    const product = {

      name: this.name,

      category: this.category,

      price: this.price,

      quantity: this.quantity

    };

    this.productService
      .addProduct(product)
      .subscribe(() => {

        alert('Product Added');

        this.loadProducts();

      });

  }

}