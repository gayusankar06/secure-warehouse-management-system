import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OrderService } from '../../services/order';
import { ProductService } from '../../services/product';
import { Sidebar } from '../layout/sidebar/sidebar';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar],
  templateUrl: './orders.html',
  styleUrl: './orders.css'
})
export class Orders implements OnInit {
  orders: any[] = [];
  filteredOrders: any[] = [];
  products: any[] = [];

  // Form
  selectedProductId: number | null = null;
  quantity: number | null = null;
  deliveryAddress = '';
  paymentMethod = 'Credit Card';

  // Stats
  totalOrders = 0;
  pendingDeliveries = 0;
  deliveredOrders = 0;
  cancelledOrders = 0;

  role = '';
  searchTerm = '';
  statusFilter = '';

  constructor(
    private orderService: OrderService,
    private productService: ProductService
  ) {
    this.role = localStorage.getItem('role') || '';
  }

  ngOnInit(): void {
    this.loadOrders();
    this.loadProducts();
  }

  loadOrders() {
    this.orderService.getOrders().subscribe((data: any) => {
      this.orders = data;
      this.applyFilters();
      this.calculateStats();
    });
  }

  loadProducts() {
    this.productService.getProducts().subscribe((data: any) => {
      this.products = data;
    });
  }

  calculateStats() {
    this.totalOrders = this.orders.length;
    this.pendingDeliveries = this.orders.filter(o => o.orderStatus === 'Pending' || o.orderStatus === 'Processing').length;
    this.deliveredOrders = this.orders.filter(o => o.orderStatus === 'Delivered').length;
    this.cancelledOrders = this.orders.filter(o => o.orderStatus === 'Cancelled').length;
  }

  applyFilters() {
    this.filteredOrders = this.orders.filter(o => {
      const matchSearch = o.id?.toString().includes(this.searchTerm) || 
                          o.trackingId?.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchStatus = this.statusFilter ? o.orderStatus === this.statusFilter : true;
      return matchSearch && matchStatus;
    });
  }

  get uniqueStatuses() {
    return Array.from(new Set(this.orders.map(o => o.orderStatus)));
  }

  getProductName(productId: number) {
    const product = this.products.find(p => p.id === productId);
    return product ? product.productName : 'Unknown Product';
  }

  getProductPrice(productId: number) {
    const product = this.products.find(p => p.id === productId);
    return product ? product.maskedPrice : '₹ *****';
  }

  addOrder() {
    if (!this.selectedProductId || !this.quantity || !this.deliveryAddress) {
      alert('Please fill all required fields');
      return;
    }

    const order = {
      productId: this.selectedProductId,
      quantity: this.quantity,
      deliveryAddress: this.deliveryAddress,
      orderStatus: 'Pending',
      trackingId: 'TRK-' + Math.floor(Math.random() * 1000000),
      createdAt: new Date().toISOString()
    };

    this.orderService.addOrder(order).subscribe(() => {
      alert('Order Created Successfully');
      this.selectedProductId = null;
      this.quantity = null;
      this.deliveryAddress = '';
      this.loadOrders();
    });
  }

  getProgress(status: string) {
    switch (status) {
      case 'Pending': return 20;
      case 'Packed': return 40;
      case 'Processing': return 40;
      case 'Shipped': return 70;
      case 'Delivered': return 100;
      default: return 0;
    }
  }

  viewDetails(order: any) {
    alert('Viewing details for Order ID: ' + order.id);
  }

  trackOrder(order: any) {
    alert('Tracking Order ID: ' + order.trackingId);
  }

  cancelOrder(order: any) {
    if(confirm('Are you sure you want to cancel this order?')) {
      alert('Order Cancelled');
      // implement cancel logic API call
    }
  }

  downloadInvoice(order: any) {
    alert('Downloading invoice for Order ID: ' + order.id);
  }
}