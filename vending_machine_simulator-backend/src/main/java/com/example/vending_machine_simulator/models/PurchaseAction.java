package com.example.vending_machine_simulator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseAction extends BaseObject {

    private Long purchaseId;
    private String action; // SELECT ITEM, PAY (INSERT MONEY), TAKE_CHANGE ,EXIT
    private String selectedItemNumber;
    private List<String> insertedMoney;

    @JsonProperty("isPayByVisa")
    private boolean isPayByVisa;
}
