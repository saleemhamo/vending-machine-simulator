
package com.example.vending_machine_simulator.models.vending_machine;

import com.example.vending_machine_simulator.models.item.SnackItem;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class SnackVendingMachine extends VendingMachine<SnackVendingMachineItem> {
    private static SnackVendingMachine instance = null;

    private SnackVendingMachine() {
    }

    public static SnackVendingMachine getInstance() {
        if (instance == null) {
            instance = new SnackVendingMachine();
        }
        return instance;
    }

    public boolean isValidItemNumber(String number) {
        return !getItems().stream()
                .filter(item -> number.equals(item.getNumber()))
                .collect(Collectors.toSet())
                .isEmpty();
    }

    public boolean isValidItemInStock(String number) {
        return !getItems().stream()
                .filter(item -> number.equals(item.getNumber()))
                .filter(item -> item.getStock() > 0)
                .collect(Collectors.toSet())
                .isEmpty();
    }

    public SnackVendingMachineItem getSnackMachineItem(String number) {
        return getItems().stream()
                .filter(item -> number.equals(item.getNumber()))
                .collect(Collectors.toSet())
                .stream().findFirst().orElse(null);
    }

}
