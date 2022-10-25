import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  productQuantity;

  @Output()
  productAdded = new EventEmitter<{
    name: string;
    quantity: number;
    status: string;
  }>();

  constructor() {}

  ngOnInit(): void {}

  onAddProduct(productNameInput: HTMLInputElement) {
    var product = {
      name: productNameInput.value,
      quantity: this.productQuantity,
      status: this.productQuantity > 0 ? 'InStock' : 'OutOfStock',
    };
    console.log(product);
    this.productAdded.emit(product);
  }
}
