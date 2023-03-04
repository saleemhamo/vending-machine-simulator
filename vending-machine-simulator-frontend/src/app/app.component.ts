import { Component } from '@angular/core';

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
  selectedProduct: any;

  // Money
  totalAmountPaid = 0;
  change;

  // Input & Message
  input: string = '';
  message: string;

  ngOnInit() {
    this.prepareCoins();
    this.fetchAndPrepareProducts();
    this.change = 2;
    this.message = 'Enter Item Number';
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

  appendToInput(n: string) {
    this.input = this.input + n;
  }

  backspace() {
    if (this.input?.length) {
      this.input = this.input.slice(0, -1);
    }
    // this.input = parseInt(this.input / 10 + '', 10);
  }

  submitInput() {}

  acceptCoin(amount) {
    console.log(amount);
  }

  payByVisa() {

  }
}

