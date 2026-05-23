import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TrackingService } from '../../services/tracking';
import { Sidebar } from '../layout/sidebar/sidebar';

@Component({
  selector: 'app-tracking',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar],
  templateUrl: './tracking.html',
  styleUrl: './tracking.css'
})
export class Tracking implements OnInit {
  shipments: any[] = [];
  trackingNumber = '';
  orderId: number | null = null;
  location = '';
  status = '';
  role = '';

  constructor(private trackingService: TrackingService) {
    this.role = localStorage.getItem('role') || '';
  }

  ngOnInit(): void {
    // If we wanted to load tracking history, we could.
  }

  loadTracking() {
    if (!this.orderId) return;
    this.trackingService.getTrackingByOrder(this.orderId).subscribe((data: any) => {
      this.shipments = data;
    });
  }

  addTracking() {
    const tracking = {
      orderId: this.orderId,
      location: this.location,
      status: this.status,
      timestamp: new Date().toISOString()
    };
    this.trackingService.addTracking(tracking).subscribe(() => {
      alert('Tracking Update Added');
      this.loadTracking();
    });
  }
}