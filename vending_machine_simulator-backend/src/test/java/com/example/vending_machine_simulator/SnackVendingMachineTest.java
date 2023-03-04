package com.example.vending_machine_simulator;

import com.example.vending_machine_simulator.models.item.SnackItem;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachineItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnackVendingMachineTest {

    private SnackVendingMachine machine;

    @BeforeEach
    void setUp() {
        machine = SnackVendingMachine.getInstance();

        SnackItem snackItem1 = new SnackItem(1L, "Chips", 0.75);
        SnackItem snackItem2 = new SnackItem(2L, "Candy", 0.50);

        // Populate the machine with some test items
        machine.addItem(new SnackVendingMachineItem(machine, snackItem1, 0, 0, 10));
        machine.addItem(new SnackVendingMachineItem(machine, snackItem2, 1, 1, 10));
    }

    @Test
    void testGetInstance() {
        // Make sure that the same instance is returned every time
        assertEquals(machine, SnackVendingMachine.getInstance());
    }

    @Test
    void testIsValidItemNumber() {
        // Test that valid item numbers return true
        assertTrue(machine.isValidItemNumber("00"));
        assertTrue(machine.isValidItemNumber("11"));

        // Test that invalid item numbers return false
        assertFalse(machine.isValidItemNumber("B1"));
        assertFalse(machine.isValidItemNumber("C2"));
    }

    @Test
    void testIsValidItemInStock() {
        // Test that valid items in stock return true
        assertTrue(machine.isValidItemInStock("00"));
        assertTrue(machine.isValidItemInStock("11"));

        // Test that valid items out of stock return false
        machine.setItemStockByNumber("00", 0);
        assertFalse(machine.isValidItemInStock("00"));

        // Test that invalid items always return false
        assertFalse(machine.isValidItemInStock("B1"));
        assertFalse(machine.isValidItemInStock("C2"));
    }

    @Test
    void testGetSnackMachineItem() {
        // Test that valid item numbers return the correct item
        assertEquals(machine.getItemByNumber("00"), machine.getSnackMachineItem("00"));
        assertEquals(machine.getItemByNumber("00"), machine.getSnackMachineItem("00"));

        // Test that invalid item numbers return null
        assertNull(machine.getSnackMachineItem("B1"));
        assertNull(machine.getSnackMachineItem("C2"));
    }
}
