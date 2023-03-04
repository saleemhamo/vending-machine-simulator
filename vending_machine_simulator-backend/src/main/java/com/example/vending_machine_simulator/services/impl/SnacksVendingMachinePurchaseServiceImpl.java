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

import static com.example.vending_machine_simulator.models.enums.ProcessState.*;

@Service
@RequiredArgsConstructor
public class SnacksVendingMachinePurchaseServiceImpl implements SnacksVendingMachinePurchaseService {

    private final SelectItemService selectItemService;
    private final InsertMoneyService insertMoneyService;
    private final DispenseItemService dispenseItemService;
    private final ReturnChangeService returnChangeService;
    private final ExitService exitService;

    private final ConcurrentMap<Long, Purchase> purchaseMap = new ConcurrentHashMap<>();
    // TODO: add queue

    @Override
    public Purchase startPurchase() {
        Purchase purchase = new Purchase();
        purchaseMap.put(purchase.getPurchaseId(), purchase);
        return purchase;
    }

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
