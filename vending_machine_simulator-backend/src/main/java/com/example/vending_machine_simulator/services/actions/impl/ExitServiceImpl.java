package com.example.vending_machine_simulator.services.actions.impl;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.services.actions.ExitService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.vending_machine_simulator.models.enums.ProcessState.*;

@Service
public class ExitServiceImpl implements ExitService {

    private final List<Purchase> purchaseHistory = new ArrayList<>();


    @Override
    public Purchase exitPurchase(Purchase purchase, PurchaseAction purchaseAction) throws Exception {
        validateExitPurchaseAction(purchaseAction, purchase);
        purchase.setProcessState(EXIT);

        purchaseHistory.add(purchase);
        return purchase;
    }

    private void validateExitPurchaseAction(PurchaseAction purchaseAction, Purchase purchase) throws Exception {
        if (!"EXIT".equals(purchaseAction.getAction())) {
            throw new Exception("Invalid Purchase Action");
        }

        if (!RETURN_CHANGE.equals(purchase.getProcessState())) {
            throw new Exception("Invalid Purchase Action");
        }

    }
}

