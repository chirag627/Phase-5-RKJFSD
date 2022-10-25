import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-newaccount',
  templateUrl: './newaccount.component.html',
  styleUrls: ['./newaccount.component.css'],
})
export class NewaccountComponent implements OnInit {
  constructor(private accountService: AccountService) {}

  ngOnInit(): void {}

  onCreateAccount(accountName: string, status: string) {
    this.accountService.addAccount(accountName, status);
  }
}
