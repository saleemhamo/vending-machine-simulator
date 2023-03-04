package com.example.vending_machine_simulator.services.actions.impl;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.enums.ProcessState;
import com.example.vending_machine_simulator.services.actions.DispenseItemService;
import org.springframework.stereotype.Service;

import static com.example.vending_machine_simulator.models.enums.ProcessState.DISPENSE_ITEM;

@Service
public class DispenseItemServiceImpl implements DispenseItemService {

    @Override
    public Purchase dispenseItem(Purchase purchase) throws Exception {
        validateDispenseItem(purchase);
        purchase.setProcessState(DISPENSE_ITEM);
        return purchase;
    }

    private void validateDispenseItem(Purchase purchase) throws Exception {
        if (!ProcessState.INSERT_MONEY.equals(purchase.getProcessState())) {
            throw new Exception("Invalid Purchase Action");
        }

        if (purchase.getBalance() < purchase.getSnackVendingMachineItem().getItem().getPrice()) {
            throw new Exception("No enough money");
        }
    }
}
