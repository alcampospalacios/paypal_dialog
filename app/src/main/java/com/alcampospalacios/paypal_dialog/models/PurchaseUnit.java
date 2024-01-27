package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PurchaseUnit {
    private String reference_id;
    private Amount amount;
    private Payee payee;
    private ArrayList<Item>  items;

    public PurchaseUnit(){}

    public PurchaseUnit(
            @NonNull String reference_id,
            @NonNull Amount amount,
            @NonNull Payee payee,
            @NonNull ArrayList<Item> items
    ) {
        this.reference_id = reference_id;
        this.amount = amount;
        this.payee = payee;
        this.items = items;
    };

    public String getReference_id() { return reference_id; }
    public void setReference_id(String value) { this.reference_id = value; }

    public Amount getAmount() { return amount; }
    public void setAmount(Amount value) { this.amount = value; }

    public Payee getPayee() { return payee; }
    public void setPayee(Payee value) { this.payee = value; }

    public ArrayList<Item>  getItems() { return items; }
    public void setItems(ArrayList<Item>  value) { this.items = value; }
}
