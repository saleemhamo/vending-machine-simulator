package com.example.vending_machine_simulator.services.actions.impl;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachineItem;
import com.example.vending_machine_simulator.services.actions.SelectItemService;
import org.springframework.stereotype.Service;

import static com.example.vending_machine_simulator.models.enums.ProcessState.SELECT_ITEM;

@Service
public class SelectItemServiceImpl implements SelectItemService {

    @Override
    public Purchase selectItem(Purchase purchase, PurchaseAction purchaseAction) throws Exception {
        validateSelectItemAction(purchaseAction);

        purchase.setProcessState(SELECT_ITEM);

        SnackVendingMachineItem snackVendingMachineItem =
                SnackVendingMachine.getInstance().getSnackMachineItem(purchaseAction.getSelectedItemNumber());
        snackVendingMachineItem.setStock(snackVendingMachineItem.getStock() - 1);
        purchase.setSnackVendingMachineItem(snackVendingMachineItem);

        return purchase;
    }

    private void validateSelectItemAction(PurchaseAction purchaseAction) throws Exception {
        if (!"SELECT_ITEM".equals(purchaseAction.getAction())) {
            throw new Exception("Invalid Purchase Action");
        }

        if (purchaseAction.getSelectedItemNumber().isEmpty()) {
            throw new Exception("Invalid Purchase Action");
        }

        if (!SnackVendingMachine.getInstance().isValidItemNumber(purchaseAction.getSelectedItemNumber())) {
            throw new Exception("Invalid Item");
        }

        if (!SnackVendingMachine.getInstance().isValidItemInStock(purchaseAction.getSelectedItemNumber())) {
            throw new Exception("Item out of stock");
        }
    }

}
