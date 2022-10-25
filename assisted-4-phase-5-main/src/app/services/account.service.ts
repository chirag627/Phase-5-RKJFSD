export class AccountService {
  accounts = [];

  addAccount(name: string, status: string) {
    this.accounts.push({ name: name, status: status });
  }

  updateStatus(id: number, newstatus: string) {
    this.accounts[id].status = newstatus;
  }

  deleteAccount(id: number) {
    this.accounts.splice(id, 1);
  }
}
