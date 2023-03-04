
package com.example.vending_machine_simulator.models.vending_machine;

import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

/**
 * This is a Singleton class that represents a Snacks Vending Machine. It extends the VendingMachine class and has a collection of SnackVendingMachineItem objects.
 * <p>
 * It provides methods for validating item numbers and checking if an item is in stock.
 */
@Getter
@Setter
public class SnackVendingMachine extends VendingMachine<SnackVendingMachineItem> {
    private static SnackVendingMachine instance = null;

    /**
     * Private constructor to prevent the instantiation of the class from outside.
     */
    private SnackVendingMachine() {
    }

    /**
     * This method returns the singleton instance of the Snacks Vending Machine.
     *
     * @return The singleton instance of the Snacks Vending Machine.
     */
    public static SnackVendingMachine getInstance() {
        if (instance == null) {
            instance = new SnackVendingMachine();
        }
        return instance;
    }


    /**
     * This method checks if the given item number is valid and exists in the vending machine.
     *
     * @param number The item number to check.
     * @return True if the item number is valid and exists in the vending machine, false otherwise.
     */
    public boolean isValidItemNumber(String number) {
        return !getItems().stream()
                .filter(item -> number.equals(item.getNumber()))
                .collect(Collectors.toSet())
                .isEmpty();
    }

    /**
     * This method checks if the given item number is valid and is currently in stock in the vending machine.
     *
     * @param number The item number to check.
     * @return True if the item number is valid and is currently in stock in the vending machine, false otherwise.
     */
    public boolean isValidItemInStock(String number) {
        return !getItems().stream()
                .filter(item -> number.equals(item.getNumber()))
                .filter(item -> item.getStock() > 0)
                .collect(Collectors.toSet())
                .isEmpty();
    }


    /**
     * This method returns the SnackVendingMachineItem object with the given item number.
     *
     * @param number The item number of the SnackVendingMachineItem to retrieve.
     * @return The SnackVendingMachineItem object with the given item number, or null if it does not exist.
     */
    public SnackVendingMachineItem getSnackMachineItem(String number) {
        return getItems().stream()
                .filter(item -> number.equals(item.getNumber()))
                .collect(Collectors.toSet())
                .stream().findFirst().orElse(null);
    }

}
