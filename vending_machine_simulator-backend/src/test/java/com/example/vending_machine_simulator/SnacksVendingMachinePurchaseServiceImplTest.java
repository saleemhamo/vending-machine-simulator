package com.example.vending_machine_simulator;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.models.enums.ProcessState;
import com.example.vending_machine_simulator.services.actions.impl.SelectItemServiceImpl;
import com.example.vending_machine_simulator.services.impl.SnacksVendingMachinePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SnacksVendingMachinePurchaseServiceImplTest {

    @Mock
    private SelectItemServiceImpl selectItemService;

    @InjectMocks
    private SnacksVendingMachinePurchaseServiceImpl purchaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartPurchase() {
        Purchase purchase = purchaseService.startPurchase();

        assertNotNull(purchase.getPurchaseId());
        assertNotNull(purchaseService.purchaseMap.get(purchase.getPurchaseId()));
    }


    @Test
    void testProcessPurchase_Invalid() throws Exception {
        Purchase purchase = purchaseService.startPurchase();
        PurchaseAction purchaseAction = new PurchaseAction();
        purchaseAction.setPurchaseId(purchase.getPurchaseId());
        purchaseAction.setAction("INVALID_ACTION"); // this should throw an exception

        assertThrows(Exception.class, () -> purchaseService.processPurchase(purchaseAction));
    }

    @Test
    void testProcessPurchase() throws Exception {
        Purchase purchase = purchaseService.startPurchase();

        // test a valid case
        PurchaseAction validPurchaseAction = new PurchaseAction();
        validPurchaseAction.setPurchaseId(purchase.getPurchaseId());
        validPurchaseAction.setAction("SELECT_ITEM");
        validPurchaseAction.setSelectedItemNumber("00");


        purchase.setProcessState(ProcessState.SELECT_ITEM);
        when(selectItemService.selectItem(any(), any())).thenReturn(purchase);
        Purchase processedPurchase = purchaseService.processPurchase(validPurchaseAction);

        assertNotNull(processedPurchase);
        assertEquals(ProcessState.SELECT_ITEM, processedPurchase.getProcessState());
    }

}
