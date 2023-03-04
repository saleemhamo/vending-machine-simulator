import { Component } from '@angular/core';
import { VendingMachineHttpService } from './main/_services/http-services/vending-machine/vending-machine.http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  // Coins
  coins = []; // TODO enum
  visaURL: string = '/assets/images/visa.jpeg';

  // Products
  products = [];

  // Money
  totalAmountPaid = 0;
  change;

  // Input & Message
  input: string = '';
  message: string;

  // Purchase
  purchase: any;

  constructor(private vendingMachineHttpService: VendingMachineHttpService) {}

  ngOnInit() {
    this.prepareCoins();
    this.fetchAndPrepareProducts();
    // this.change = 2;
    this.message = 'Enter Item Number';
    this.vendingMachineHttpService
      .getVendingMachineDetails()
      .subscribe((data) => {
        console.log(data);
        this.products = data.items;
        this.startPurchase();
      });
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

  fetchAndPrepareProducts() {
    // Fetch from BE
    this.products = [
      {
        id: '00',
        imageURL: '/assets/images/SodaCan.png',
        name: 'Sprite',
        stock: 5,
        price: 1.5,
      },
    ];
  }

  // Purchase Process
  startPurchase() {
    this.vendingMachineHttpService.startPurchase().subscribe((purchase) => {
      this.purchase = purchase;
      console.log('Started');
      console.log(this.purchase);

      this.message = 'Select item number';
      // enable input
    });
  }

  submitInput() {
    // choose item
    const purchaseAction = {
      purchaseId: this.purchase.purchaseId,
      action: 'SELECT_ITEM',
      selectedItemNumber: this.input,
    };

    this.vendingMachineHttpService.processPurchase(purchaseAction).subscribe(
      (purchase) => {
        this.purchase = purchase;
        this.message = 'Insert Money';
        // disable inputs
        // enable selecting money
      },
      (error) => {
        console.log(error);
      }
    );
  }

  // Input methods
  appendToInput(n: string) {
    this.input = this.input + n;
  }

  backspace() {
    if (this.input?.length) {
      this.input = this.input.slice(0, -1);
    }
  }

  acceptCoin(amount) {
    // add selection to  this.purchase;
    const purchaseAction = {
      purchaseId: this.purchase.purchaseId,
      action: 'INSERT_MONEY',
      insertedMoney: this.purchase.insertedMoney,
      isPayByVisa: false,
    };

    this.processInsertMoneyRequest(purchaseAction);
  }

  payByVisa() {
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
        // disable inputs
        // disable selecting money

        // dispence item

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
