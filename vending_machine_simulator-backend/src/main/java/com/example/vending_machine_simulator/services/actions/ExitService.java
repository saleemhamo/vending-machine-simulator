package com.example.vending_machine_simulator.services.actions;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;

import static com.example.vending_machine_simulator.models.enums.ProcessState.EXIT;

public interface ExitService {
    Purchase exitPurchase(Purchase purchase, PurchaseAction purchaseAction) throws Exception;
}
