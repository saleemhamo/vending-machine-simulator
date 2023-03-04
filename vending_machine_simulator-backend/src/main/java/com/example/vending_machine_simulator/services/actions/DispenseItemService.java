package com.example.vending_machine_simulator.services.actions;

import com.example.vending_machine_simulator.models.Purchase;

public interface DispenseItemService {

    Purchase dispenseItem(Purchase purchase) throws Exception;

}
