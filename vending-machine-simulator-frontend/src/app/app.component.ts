import { Component, OnDestroy } from '@angular/core';
import { VendingMachineHttpService } from './main/_services/http-services/vending-machine/vending-machine.http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnDestroy {
  // Coins
  coins = []; // TODO enum
  visaURL: string = '/assets/images/visa.jpeg';

  // Products
  products = [];

  // Money
  totalAmountPaid = 0;
  change;
  paidMoney = [];

  // Input & Message
  input: string = '';
  message: string;

  // Purchase
  purchase: any;

  allowPayment = false;
  allowInput = false;

  constructor(private vendingMachineHttpService: VendingMachineHttpService) {}

  ngOnInit() {
    this.prepareCoins();
    this.message = 'Enter Item Number';
    this.vendingMachineHttpService
      .getVendingMachineDetails()
      .subscribe((data) => {
        console.log(data);
        this.products = this.prepareRowsAndColumns(data);
        this.paidMoney = [];
        this.change = null;
        debugger;
        this.startPurchase();
      });
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

  prepareRowsAndColumns(data) {
    const rows = data.numberOfRows;
    const cols = data.numberOfCols;
    const list = data.items;
    const result: any[][] = [];
    for (let i = 0; i < rows; i++) {
      const row: any[] = [];
      for (let j = 0; j < cols; j++) {
        const itemIndex = i * cols + j;
        if (itemIndex < list.length) {
          const product = list[itemIndex];
          product.imageURL =
            'https://www.skittles.com/cdn-cgi/image/width=600,height=600,f=auto,quality=90/sites/g/files/fnmzdf586/files/migrate-product-files/d4yf2umrnqkfnaoyyb62.png';
          row.push(product);
        } else {
          row.push(null);
        }
      }
      result.push(row);
    }

    return result;
  }

  prepareCoins() {
    this.coins = [
      {
        imageURL: '/assets/images/10c.jpeg',
        alt: '10c',
        amount: '10c',
      },
      {
        imageURL: '/assets/images/20c.jpeg',
        alt: '20c',
        amount: '20c',
      },
      {
        imageURL: '/assets/images/50c.jpeg',
        alt: '50c',
        amount: '50c',
      },
      {
        imageURL: '/assets/images/1d.jpeg',
        alt: '$1',
        amount: '$1',
      },
      {
        imageURL: '/assets/images/20d.jpeg',
        alt: '$20',
        amount: '$20',
      },
      {
        imageURL: '/assets/images/50d.jpeg',
        alt: '$50',
        amount: '$50',
      },
    ];
  }

  // Purchase Process
  startPurchase() {
    this.vendingMachineHttpService.startPurchase().subscribe((purchase) => {
      this.purchase = purchase;
      this.message = 'Select item number';
      this.allowInput = true;
    });
  }

  submitInput() {
    if (!this.input?.length || !this.allowInput) {
      return;
    }
    // choose item
    const purchaseAction = {
      purchaseId: this.purchase.purchaseId,
      action: 'SELECT_ITEM',
      selectedItemNumber: this.input,
    };

    this.vendingMachineHttpService.processPurchase(purchaseAction).subscribe(
      (purchase) => {
        this.purchase = purchase;
        this.message =
          'Price: ' +
          purchase.snackVendingMachineItem.item.price +
          ', Please insert money';
        this.allowPayment = true;
        this.allowInput = false;
      },
      (error) => {
        this.message = 'Invalid input';
        console.log(error);
      }
    );
  }

  // Input methods
  appendToInput(n: string) {
    if (!this.allowInput) {
      return;
    }
    this.input = this.input + n;
  }

  backspace() {
    if (!this.allowInput) {
      return;
    }
    if (this.input?.length) {
      this.input = this.input.slice(0, -1);
    }
  }

  calculateAmount() {
    let amount = 0;
    this.paidMoney.forEach(money => {
      switch (money) {
        case '10c':
          amount += 0.1;
          break;
        case '20c':
          amount += 0.2;
          break;
        case '50c':
          amount += 0.5;
          break;
        case '$1':
          amount += 1;
          break;
        case '$20':
          amount += 20;
          break;
        case '$50':
          amount += 50;
          break;
        default:
          break;
      }
    });

    console.log(this.paidMoney);
    console.log(amount);

    this.totalAmountPaid = amount;
  }

  acceptCoin(amount) {
    if (!this.allowPayment) {
      return;
    }
    this.paidMoney.push(amount);
    this.calculateAmount();
    // add selection to  this.purchase;
    const purchaseAction = {
      purchaseId: this.purchase.purchaseId,
      action: 'INSERT_MONEY',
      insertedMoney: this.paidMoney,
      isPayByVisa: false,
    };

    this.processInsertMoneyRequest(purchaseAction);
  }

  payByVisa() {
    if (!this.allowPayment) {
      return;
    }

    const purchaseAction = {
      purchaseId: this.purchase.purchaseId,
      action: 'INSERT_MONEY',
      insertedMoney: this.purchase.insertedMoney,
      isPayByVisa: true,
    };

    this.processInsertMoneyRequest(purchaseAction);
  }

  processInsertMoneyRequest(purchaseAction) {
    this.vendingMachineHttpService.processPurchase(purchaseAction).subscribe(
      (purchase) => {
        this.purchase = purchase;
        this.allowInput = false;
        this.allowPayment = false;

        this.message = 'Take your item';

        this.processReturnChangeRequest();
      },
      (error) => {
        // not enough money
        console.log(error);
      }
    );
  }

  processReturnChangeRequest() {
    const purchaseAction = {
      purchaseId: this.purchase.purchaseId,
      action: 'RETURN_CHANGE',
    };

    this.vendingMachineHttpService.processPurchase(purchaseAction).subscribe(
      (purchase) => {
        this.purchase = purchase;
        if (this.purchase.change > 0) {
          this.change = this.purchase.change;
        } else {
          this.change = 0;
        }

        this.processExitPurchase();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  processExitPurchase() {
    const purchaseAction = {
      purchaseId: this.purchase.purchaseId,
      action: 'EXIT',
    };

    this.vendingMachineHttpService.processPurchase(purchaseAction).subscribe(
      (purchase) => {
        this.purchase = purchase;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
