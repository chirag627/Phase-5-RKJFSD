import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  productId = 10;
  productStatus = 'InStock';

  constructor() {}

  ngOnInit(): void {}

  getColor() {
    return this.productStatus === 'InStock' ? 'green' : 'red';
  }
}
