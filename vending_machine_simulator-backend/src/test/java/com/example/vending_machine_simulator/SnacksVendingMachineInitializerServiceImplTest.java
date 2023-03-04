package com.example.vending_machine_simulator;

import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.services.impl.SnacksVendingMachineInitializerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SnacksVendingMachineInitializerServiceImplTest {

    @InjectMocks
    private SnacksVendingMachineInitializerServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetItemIndexInOriginalList() {
        // Create a new SnackVendingMachine instance
        SnackVendingMachine snackVendingMachine = SnackVendingMachine.getInstance();
        snackVendingMachine.setNumberOfRows(3);
        snackVendingMachine.setNumberOfCols(4);

        // Initialize the service implementation
        SnacksVendingMachineInitializerServiceImpl service = new SnacksVendingMachineInitializerServiceImpl();

        // Test the method for various row and column values
        Assertions.assertEquals(0, service.getItemIndexInOriginalList(0, 0));
        Assertions.assertEquals(3, service.getItemIndexInOriginalList(0, 3));
        Assertions.assertEquals(4, service.getItemIndexInOriginalList(1, 0));
        Assertions.assertEquals(11, service.getItemIndexInOriginalList(2, 3));
    }

}