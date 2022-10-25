import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ecomm',
  templateUrl: './ecomm.component.html',
  styleUrls: ['./ecomm.component.css'],
})
export class EcommComponent implements OnInit {
  products = [];

  constructor() {}

  ngOnInit(): void {}

  onProductAdded(data) {
    this.products.push({
      name: data.name,
      quantity: data.quantity,
      status: data.status,
    });
    console.log(this.products);
  }
}
