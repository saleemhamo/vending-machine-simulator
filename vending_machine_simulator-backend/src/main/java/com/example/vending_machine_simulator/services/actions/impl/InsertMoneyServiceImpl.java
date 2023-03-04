package com.example.vending_machine_simulator.services.actions.impl;

import com.example.vending_machine_simulator.models.Purchase;
import com.example.vending_machine_simulator.models.PurchaseAction;
import com.example.vending_machine_simulator.models.enums.Money;
import com.example.vending_machine_simulator.services.actions.InsertMoneyService;
import org.springframework.stereotype.Service;

import static com.example.vending_machine_simulator.models.enums.ProcessState.INSERT_MONEY;

@Service
public class InsertMoneyServiceImpl implements InsertMoneyService {

    @Override
    public Purchase insertMoney(Purchase purchase, PurchaseAction purchaseAction) throws Exception {
        validateInsertMoneyAction(purchaseAction);
        purchase.setProcessState(INSERT_MONEY);

        double insertedMoneyAmount = calculateInsertedMoneyAmount(purchaseAction, purchase);
        purchase.incrementBalance(insertedMoneyAmount);
        if (!purchaseAction.isPayByVisa() && purchase.hasSufficientBalance()) {
            throw new Exception("No enough money");
        }


        return purchase;

    }

    private void validateInsertMoneyAction(PurchaseAction purchaseAction) throws Exception {
        if (!"INSERT_MONEY".equals(purchaseAction.getAction())) {
            throw new Exception("Invalid Purchase Action");
        }

        if (purchaseAction.getInsertedMoney().isEmpty()) {
            throw new Exception("Invalid Purchase Action");
        }

        if (purchaseAction.getInsertedMoney().contains(Money.TWENTY_NIS)) {
            throw new Exception("Only USD accepted");
        }
    }


    private double calculateInsertedMoneyAmount(PurchaseAction purchaseAction, Purchase purchase) {
        double amount = 0;
        if (purchaseAction.isPayByVisa()) {
            amount += purchase.getSnackVendingMachineItem().getItem().getPrice();
        }

        for (Money money : purchaseAction.getInsertedMoney()) {
            amount += money.getNumericValue();
        }

        return amount;
    }

}
