package com.example.vending_machine_simulator.models;

import com.example.vending_machine_simulator.models.enums.Money;
import com.example.vending_machine_simulator.models.enums.ProcessState;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachineItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class PurchaseAction extends BaseObject {

    private Long purchaseId;
    private String action; // SELECT ITEM, PAY (INSERT MONEY), TAKE_CHANGE ,EXIT
    private String selectedItemNumber;
    private List<Money> insertedMoney;

    @JsonProperty("isPayByVisa")
    private boolean isPayByVisa;
}
