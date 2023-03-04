
package com.example.vending_machine_simulator.models.vending_machine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnackVendingMachine extends VendingMachine {
    private static SnackVendingMachine instance = null;

    private int numberOfRows;
    private int numberOdCols;



    private SnackVendingMachine() {

    }

    public static SnackVendingMachine getInstance() {
        if (instance == null) {
            instance = new SnackVendingMachine();
        }
        return instance;
    }

}
