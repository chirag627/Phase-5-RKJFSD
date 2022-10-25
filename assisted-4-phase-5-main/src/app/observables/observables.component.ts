import { Component, OnInit } from '@angular/core';
import { interval, Observable, Subscription } from 'rxjs';
import { map, filter } from 'rxjs/operators';

@Component({
  selector: 'app-observables',
  templateUrl: './observables.component.html',
  styleUrls: ['./observables.component.css'],
})
export class ObservablesComponent implements OnInit {
  intervalsubcscription: Subscription;
  customIntervalSubscription: Subscription;
  constructor() {}

  ngOnInit(): void {
    // this.intervalsubcscription = interval(1000).subscribe((count) => {
    //   console.log(count);
    // });

    const customInterval = Observable.create((observer) => {
      let count = 0;
      setInterval(() => {
        observer.next(count);
        count++;
        if (count > 9) {
          observer.error(new Error('count is greater than 9'));
        }
        if (count === 9) {
          observer.complete();
        }
      }, 1000);
    });

    this.customIntervalSubscription = customInterval
      .pipe(
        filter((data: number) => {
          return data % 2 === 0;
        }),
        map((data: number) => {
          return data * 10;
        })
      )
      .subscribe(
        (count) => {
          console.log(count);
        },
        (error) => {
          console.log(error);
          alert(error.message);
        },
        () => {
          console.log('completed');
        }
      );
  }

  ngOnDestroy(): void {
    // this.intervalsubcscription.unsubscribe();
    this.customIntervalSubscription.unsubscribe();
  }
}
