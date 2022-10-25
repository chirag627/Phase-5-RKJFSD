import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-userview',
  templateUrl: './userview.component.html',
  styleUrls: ['./userview.component.css'],
})
export class UserviewComponent implements OnInit {
  paramsSubscription: Subscription;
  @Input()
  user: {
    id: number;
    name: string;
  };

  constructor(private currentRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.user = {
      id: this.currentRoute.snapshot.params['id'],
      name: this.currentRoute.snapshot.params['name'],
    };
    this.paramsSubscription = this.currentRoute.params.subscribe(
      (params: Params) => {
        (this.user.id = params['id']), (this.user.name = params['name']);
      }
    );
  }
}
