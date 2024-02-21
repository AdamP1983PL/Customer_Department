package com.customer_department.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaxValue {
    ZERO(0), TWENTY_THREE(23);

    private final int value;

}
