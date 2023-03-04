package com.example.vending_machine_simulator.services.actions.impl;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachineItem;
import com.example.vending_machine_simulator.services.actions.SelectItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.example.vending_machine_simulator.models.enums.ProcessState.SELECT_ITEM;

@Service
public class SelectItemServiceImpl implements SelectItemService {

    /**
     * Method to select an item from the vending machine and update the purchase object with the selected item and its
     * state
     *
     * @param purchase       the purchase object to be updated
     * @param purchaseAction the purchase action containing the selected item number and action type
     * @return the updated purchase object
     * @throws Exception if the purchase action is invalid or the selected item is not available in the vending machine
     */
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

    /**
     * Method to validate the purchase action and ensure that it contains a valid action type and selected item number
     * that is available in the vending machine
     *
     * @param purchaseAction the purchase action to be validated
     * @throws Exception if the purchase action is invalid or the selected item is not available in the vending machine
     */
    private void validateSelectItemAction(PurchaseAction purchaseAction) throws Exception {
        if (!"SELECT_ITEM".equals(purchaseAction.getAction())) {
            throw new Exception("Invalid Purchase Action");
        }

        if (StringUtils.isEmpty(purchaseAction.getSelectedItemNumber())) {
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
