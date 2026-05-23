import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product';
import { Sidebar } from '../layout/sidebar/sidebar';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar],
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class Products implements OnInit {
  products: any[] = [];
  filteredProducts: any[] = [];
  
  // Form fields
  productName = '';
  category = '';
  sellingPrice: number | null = null;
  supplierPrice: number | null = null;
  quantity: number | null = null;
  secureFlag = false;

  // Search & Filter
  searchTerm = '';
  categoryFilter = '';
  stockFilter = '';

  // Dashboard Stats
  totalProducts = 0;
  lowStockCount = 0;
  secureCount = 0;
  categoriesCount = 0;

  role = '';
  editMode = false;
  editingId: number | null = null;

  constructor(private productService: ProductService) {
    this.role = localStorage.getItem('role') || '';
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getProducts().subscribe((data: any) => {
      this.products = data;
      this.applyFilters();
      this.calculateStats();
    });
  }

  calculateStats() {
    this.totalProducts = this.products.length;
    this.lowStockCount = this.products.filter(p => p.stockStatus === 'Low Stock' || p.stockStatus === 'Out of Stock').length;
    this.secureCount = this.products.filter(p => p.secureFlag).length;
    const categories = new Set(this.products.map(p => p.category));
    this.categoriesCount = categories.size;
  }

  applyFilters() {
    this.filteredProducts = this.products.filter(p => {
      const matchSearch = p.productName?.toLowerCase().includes(this.searchTerm.toLowerCase()) || 
                          p.maskedCode?.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchCategory = this.categoryFilter ? p.category === this.categoryFilter : true;
      const matchStock = this.stockFilter ? p.stockStatus === this.stockFilter : true;
      return matchSearch && matchCategory && matchStock;
    });
  }

  get uniqueCategories() {
    return Array.from(new Set(this.products.map(p => p.category)));
  }

  get uniqueStockStatuses() {
    return Array.from(new Set(this.products.map(p => p.stockStatus)));
  }

  resetForm() {
    this.productName = '';
    this.category = '';
    this.sellingPrice = null;
    this.supplierPrice = null;
    this.quantity = null;
    this.secureFlag = false;
    this.editMode = false;
    this.editingId = null;
  }

  validateForm() {
    if (!this.productName || !this.category || this.sellingPrice === null || this.quantity === null) {
      alert('Please fill all required fields');
      return false;
    }
    if (this.sellingPrice <= 0) {
      alert('Selling price must be greater than 0');
      return false;
    }
    if (this.quantity < 0) {
      alert('Quantity cannot be negative');
      return false;
    }
    return true;
  }

  saveProduct() {
    if (!this.validateForm()) return;

    const product = {
      productName: this.productName,
      category: this.category,
      sellingPrice: this.sellingPrice,
      supplierPrice: this.supplierPrice,
      quantity: this.quantity,
      secureFlag: this.secureFlag
    };

    if (this.editMode && this.editingId) {
      this.productService.updateProduct(this.editingId, product).subscribe(() => {
        alert('Product Updated');
        this.resetForm();
        this.loadProducts();
      });
    } else {
      this.productService.addProduct(product).subscribe(() => {
        alert('Product Added');
        this.resetForm();
        this.loadProducts();
      });
    }
  }

  editProduct(p: any) {
    this.editMode = true;
    this.editingId = p.id;
    this.productName = p.productName;
    this.category = p.category;
    this.sellingPrice = p.sellingPrice;
    this.supplierPrice = p.supplierPrice;
    this.quantity = p.quantity;
    this.secureFlag = p.secureFlag;
    window.scrollTo(0, 0);
  }

  deleteProduct(id: number) {
    if(confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe(() => {
        this.loadProducts();
      });
    }
  }
}