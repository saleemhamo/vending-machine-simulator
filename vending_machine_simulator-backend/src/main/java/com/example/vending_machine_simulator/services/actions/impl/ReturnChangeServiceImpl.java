
package com.example.vending_machine_simulator.services.actions.impl;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.services.actions.ReturnChangeService;

import static com.example.vending_machine_simulator.models.enums.ProcessState.DISPENSE_ITEM;
import static com.example.vending_machine_simulator.models.enums.ProcessState.RETURN_CHANGE;

public class ReturnChangeServiceImpl implements ReturnChangeService {


    @Override
    public Purchase returnChange(Purchase purchase, PurchaseAction purchaseAction) throws Exception {
        validateReturnChangeAction(purchaseAction, purchase);
        purchase.setProcessState(RETURN_CHANGE);

        if (purchase.getBalance() > purchase.getSnackVendingMachineItem().getItem().getPrice()) {
            purchase.setChange(purchase.getBalance() - purchase.getSnackVendingMachineItem().getItem().getPrice());
        }

        return purchase;
    }

    private void validateReturnChangeAction(PurchaseAction purchaseAction, Purchase purchase) throws Exception {
        if (!"RETURN_CHANGE".equals(purchaseAction.getAction())) {
            throw new Exception("Invalid Purchase Action");
        }

        if (!DISPENSE_ITEM.equals(purchase.getProcessState())) {
            throw new Exception("Invalid Purchase Action");
        }

    }
}
