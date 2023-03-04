package com.example.vending_machine_simulator.services.actions;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;

public interface SelectItemService {

    Purchase selectItem(Purchase purchase, PurchaseAction purchaseAction) throws Exception;
}
