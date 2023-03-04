package com.example.vending_machine_simulator.models;

import com.example.vending_machine_simulator.models.enums.Money;
import com.example.vending_machine_simulator.models.enums.ProcessState;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachineItem;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Purchase extends BaseObject {

    private static Long purchaseIdCounter = 0L;

    private Long purchaseId;
    private LocalTime startTime;
    private ProcessState processState;
    private SnackVendingMachineItem snackVendingMachineItem;
    private List<Money> insertedMoney;
    private double balance;
    private double change;

    public Purchase() {
        this.purchaseId = purchaseIdCounter;
        purchaseIdCounter++;
        this.startTime = LocalTime.now();
        this.processState = ProcessState.START;
        this.insertedMoney = new ArrayList<>();
        this.balance = 0;
    }

    public double incrementBalance(double amount) {
        balance += amount;
        return balance;
    }

    public boolean hasSufficientBalance() {
        return balance >= snackVendingMachineItem.getItem().getPrice();
    }
}
