package com.example.vending_machine_simulator;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.models.item.SnackItem;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachineItem;
import com.example.vending_machine_simulator.services.actions.impl.SelectItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.vending_machine_simulator.models.enums.ProcessState.SELECT_ITEM;
import static org.junit.jupiter.api.Assertions.*;

public class SelectItemServiceImplTest {

    private SelectItemServiceImpl selectItemService;
    private SnackVendingMachineItem snackVendingMachineItem;
    private SnackVendingMachine machine;

    @BeforeEach
    void setUp() {
        machine = SnackVendingMachine.getInstance();
        selectItemService = new SelectItemServiceImpl();
        SnackItem snackItem1 = new SnackItem(1L, "Test Snack", 0.75);
        snackVendingMachineItem = new SnackVendingMachineItem(machine, snackItem1, 0, 0, 5);
        machine.setItems(new ArrayList<>());
        machine.getItems().add(snackVendingMachineItem);
    }

    @Test
    void testSelectItem() throws Exception {
        Purchase purchase = new Purchase();
        PurchaseAction purchaseAction = new PurchaseAction();
        purchaseAction.setPurchaseId(purchase.getPurchaseId());
        purchaseAction.setAction("SELECT_ITEM");
        purchaseAction.setSelectedItemNumber("00");


        purchase = selectItemService.selectItem(purchase, purchaseAction);

        assertNotNull(purchase);
        assertEquals(SELECT_ITEM, purchase.getProcessState());
        assertEquals(snackVendingMachineItem, purchase.getSnackVendingMachineItem());
        assertEquals(4, snackVendingMachineItem.getStock());
    }

    @Test
    void testSelectItemWithInvalidAction() {
        Purchase purchase = new Purchase();
        PurchaseAction purchaseAction = new PurchaseAction();
        purchaseAction.setPurchaseId(purchase.getPurchaseId());
        purchaseAction.setAction("INVALID");
        purchaseAction.setSelectedItemNumber("00");

        assertThrows(Exception.class, () -> selectItemService.selectItem(purchase, purchaseAction));
    }

    @Test
    void testSelectItemWithInvalidItemNumber() {
        Purchase purchase = new Purchase();
        PurchaseAction purchaseAction = new PurchaseAction();
        purchaseAction.setPurchaseId(purchase.getPurchaseId());
        purchaseAction.setAction("SELECT_ITEM");
        purchaseAction.setSelectedItemNumber("999");
        assertThrows(Exception.class, () -> selectItemService.selectItem(purchase, purchaseAction));
    }

    @Test
    void testSelectItemWithEmptyItemNumber() {
        Purchase purchase = new Purchase();
        PurchaseAction purchaseAction = new PurchaseAction();
        purchaseAction.setPurchaseId(purchase.getPurchaseId());
        purchaseAction.setAction("SELECT_ITEM");
        purchaseAction.setSelectedItemNumber("");

        assertThrows(Exception.class, () -> selectItemService.selectItem(purchase, purchaseAction));
    }

    @Test
    void testSelectItemWithItemOutOfStock() {
        Purchase purchase = new Purchase();
        PurchaseAction purchaseAction = new PurchaseAction();
        purchaseAction.setPurchaseId(purchase.getPurchaseId());
        purchaseAction.setAction("SELECT_ITEM");
        purchaseAction.setSelectedItemNumber("00");
        snackVendingMachineItem.setStock(0);

        assertThrows(Exception.class, () -> selectItemService.selectItem(purchase, purchaseAction));
    }

}
