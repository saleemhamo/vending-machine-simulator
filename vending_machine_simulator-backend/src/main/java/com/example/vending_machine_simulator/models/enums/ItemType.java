package com.example.vending_machine_simulator.models.enums;

public enum ItemType {
    SNACKS("Snacks"),
    COFFEE("Coffee"),
    DRINKS("Drinks"),
    OTHER("Other");

    private final String value;

    private ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}