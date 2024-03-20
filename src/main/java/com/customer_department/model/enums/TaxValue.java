package com.customer_department.model.enums;


public enum TaxValue {
    ZERO(0),
    TWENTY_THREE(23);

    private final int value;

    TaxValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
