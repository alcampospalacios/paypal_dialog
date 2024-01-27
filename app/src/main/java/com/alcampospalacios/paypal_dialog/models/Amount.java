package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

public class Amount {
    private String currency_code;
    private String value;
    private Breakdown breakdown;

    public Amount(){}

    public Amount(
            @NonNull String currency_code,
            @NonNull String value,
            @NonNull Breakdown breakdown
    ) {
        this.currency_code = currency_code;
        this.value = value;
        this.breakdown = breakdown;
    };

    public String getCurrency_code() { return currency_code; }
    public void setCurrency_code(String value) { this.currency_code = value; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public Breakdown getBreakdown() { return breakdown; }
    public void setBreakdown(Breakdown value) { this.breakdown = value; }
}
