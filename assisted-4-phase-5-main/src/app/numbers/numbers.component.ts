import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-numbers',
  templateUrl: './numbers.component.html',
  styleUrls: ['./numbers.component.css'],
})
export class NumbersComponent implements OnInit {
  showNumbers = true;
  numbers = [1, 2, 3, 4, 523, 46, 75, 12, 92, 133];
  boxNumber = 10;

  constructor() {}

  ngOnInit(): void {}

  toggleShow() {
    this.showNumbers = !this.showNumbers;
  }
}
