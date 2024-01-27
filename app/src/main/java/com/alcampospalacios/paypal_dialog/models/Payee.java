package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

public class Payee {
    private String email_address;
    private String merchant_id;

    public Payee() {}

    public Payee(
            @NonNull String email_address,
            @NonNull String merchant_id
    ) {
        this.email_address = email_address;
        this.merchant_id = merchant_id;
    };

    public String getEmail_address() { return email_address; }
    public void setEmail_address(String value) { this.email_address = value; }

    public String getMerchant_id() { return merchant_id; }
    public void setMerchant_id(String value) { this.merchant_id = value; }
}
