package com.example.vending_machine_simulator.models.vending_machine;

import com.example.vending_machine_simulator.models.BaseObject;
import com.example.vending_machine_simulator.models.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class VendingMachineItem<T extends Item> extends BaseObject {

    private VendingMachine vendingMachine;
    private T item;

    private String number;
    private int row;
    private int column;
    private int stock;

    public VendingMachineItem(VendingMachine vendingMachine, T item, int row, int column, int stock) {
        this.vendingMachine = vendingMachine;
        this.item = item;
        this.row = row;
        this.column = column;
        this.stock = stock;
        this.calculateItemNumber();
    }

    private String calculateItemNumber() {
        this.number = String.format("{}{}", row, column);
        return this.number;
    }

}
