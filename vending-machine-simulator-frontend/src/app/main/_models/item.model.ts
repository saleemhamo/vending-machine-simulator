export class Item {
  type: ItemType;
  id: number;
  displayName: string;
  price: number;

  item: Item;
  number: string;
  row: number;
  col: number;
  stock: number;

  constructor(
    type: ItemType,
    id?: number,
    displayName?: string,
    price?: number
  ) {
    this.type = type;
    this.id = id;
    this.displayName = displayName;
    this.price = price;
  }
}

export enum ItemType {
  SNACKS = 'Snacks',
  COFFEE = 'Coffee',
  DRINKS = 'Drinks',
  OTHER = 'Other',
}
