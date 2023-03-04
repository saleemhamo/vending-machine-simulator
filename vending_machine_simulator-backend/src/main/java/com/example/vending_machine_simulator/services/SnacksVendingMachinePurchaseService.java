package com.example.vending_machine_simulator.services;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;

public interface SnacksVendingMachinePurchaseService {

    Purchase startPurchase();

    Purchase processPurchase(PurchaseAction purchaseAction) throws Exception;

}
