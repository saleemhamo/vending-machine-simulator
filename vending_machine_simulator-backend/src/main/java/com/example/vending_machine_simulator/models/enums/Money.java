package com.example.vending_machine_simulator.models.enums;

public enum Money {
    TEN_CENTS("10c", 0.1),
    TWENTY_CENTS("20c", 0.2),
    FIFTY_CENTS("50c", 0.5),
    ONE_DOLLAR("$1", 1),
    TWENTY_DOLLARS("$20", 20),
    FIFTY_DOLLARS("$50", 50),
    TWENTY_NIS("20NIS", 00);

    private final String value;
    private final double numericValue;

    private Money(String value, double numericValue) {
        this.value = value;
        this.numericValue = numericValue;
    }

    public String getValue() {
        return value;
    }

    public double getNumericValue() {
        return numericValue;
    }
}