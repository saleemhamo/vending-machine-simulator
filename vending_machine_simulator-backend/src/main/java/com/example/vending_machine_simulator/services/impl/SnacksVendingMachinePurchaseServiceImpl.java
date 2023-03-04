package com.example.vending_machine_simulator.services.impl;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.services.SnacksVendingMachinePurchaseService;
import com.example.vending_machine_simulator.services.actions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This class implements the SnacksVendingMachinePurchaseService interface and provides the functionality to process a purchase
 * made by a user of a vending machine. It uses various actions provided by the services.actions package to carry out the
 * steps in the purchase process, such as selecting an item, inserting money, dispensing the item, returning change and exiting
 * the purchase.
 * The purchase process is tracked using a ConcurrentHashMap, where the purchaseId is the key and the value is the Purchase object.
 *
 * @author Saleem Hamo
 */
@Service
@RequiredArgsConstructor
public class SnacksVendingMachinePurchaseServiceImpl implements SnacksVendingMachinePurchaseService {

    private final SelectItemService selectItemService;
    private final InsertMoneyService insertMoneyService;
    private final DispenseItemService dispenseItemService;
    private final ReturnChangeService returnChangeService;
    private final ExitService exitService;

    // The purchase process is tracked using a ConcurrentHashMap, where the purchaseId is the key and the value is the Purchase object.
    public final ConcurrentMap<Long, Purchase> purchaseMap = new ConcurrentHashMap<>();

    // TODO {Saleem}: Add queue


    /**
     * This method creates a new Purchase object and adds it to the ConcurrentHashMap. The Purchase object is identified using a purchaseId.
     *
     * @return Purchase object that was created
     */
    @Override
    public Purchase startPurchase() {
        Purchase purchase = new Purchase();
        purchaseMap.put(purchase.getPurchaseId(), purchase);
        return purchase;
    }

    /**
     * This method processes the given PurchaseAction object and returns the updated Purchase object.
     *
     * @param purchaseAction PurchaseAction object containing details of the user's purchase action
     * @return updated Purchase object
     * @throws Exception if the purchaseAction object is invalid or if the action is not valid
     */
    @Override
    public Purchase processPurchase(PurchaseAction purchaseAction) throws Exception {
        validatePurchaseAction(purchaseAction);

        Purchase purchase = purchaseMap.get(purchaseAction.getPurchaseId());

        switch (purchaseAction.getAction()) {
            case "SELECT_ITEM":
                return selectItemService.selectItem(purchase, purchaseAction);
            case "INSERT_MONEY":
                Purchase purchaseWithAcceptedMoney = insertMoneyService.insertMoney(purchase, purchaseAction);
                return dispenseItemService.dispenseItem(purchaseWithAcceptedMoney);
            case "RETURN_CHANGE":
                return returnChangeService.returnChange(purchase, purchaseAction);
            case "EXIT":
                Purchase donePurchase = exitService.exitPurchase(purchase, purchaseAction);
                purchaseMap.remove(donePurchase.getPurchaseId());
                return donePurchase;
            default:
                throw new Exception("Invalid Purchase Action");
        }
    }

    /**
     * This method validates the given PurchaseAction object to ensure that it is not null and corresponds to a valid purchase.
     *
     * @param purchaseAction PurchaseAction object to be validated
     * @throws Exception if the purchaseAction object is null or if it does not correspond to a valid purchase
     */
    private void validatePurchaseAction(PurchaseAction purchaseAction) throws Exception {
        if (Objects.isNull(purchaseAction.getPurchaseId())) {
            throw new Exception("Invalid Purchase");
        }

        Purchase purchase = purchaseMap.get(purchaseAction.getPurchaseId());
        if (Objects.isNull(purchase)) {
            throw new Exception("Invalid Purchase");
        }

        // not being served by another thread
    }


}
