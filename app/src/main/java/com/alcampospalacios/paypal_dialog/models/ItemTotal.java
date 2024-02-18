package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

public class ItemTotal {
    private String currency_code;
    private String value;

    public ItemTotal() {}

    public ItemTotal(
            @NonNull String currency_code,
            @NonNull String value
    ) {
        this.currency_code = currency_code;
        this.value = value;
    };

    public String getCurrency_code() { return currency_code; }
    public void setCurrency_code(String value) { this.currency_code = value; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
