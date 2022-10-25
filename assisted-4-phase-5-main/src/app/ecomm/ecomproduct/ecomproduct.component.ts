import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-ecomproduct',
  templateUrl: './ecomproduct.component.html',
  styleUrls: ['./ecomproduct.component.css'],
})
export class EcomproductComponent implements OnInit {
  @Input()
  productElement: {
    name: string;
    quantity: number;
    status: string;
  };

  constructor() {}

  ngOnInit(): void {}
}
