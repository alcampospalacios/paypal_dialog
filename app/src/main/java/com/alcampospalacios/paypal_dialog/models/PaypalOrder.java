package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PaypalOrder {
    private String id;
    private String intent;
    private String status;
    private ArrayList<PurchaseUnit> purchase_units;

     double amountOrder;
     double amountShipping;
     String currencyCode;

    public PaypalOrder(
            @NonNull String id,
            @NonNull String intent,
            @NonNull String status,
            @NonNull ArrayList<PurchaseUnit> purchase_units
    ) {
        this.id = id;
        this.intent = intent;
        this.status = status;
        this.purchase_units = purchase_units;
        this.amountOrder = 0.0;
        this.amountShipping = 0.0;
        this.currencyCode = "";
    };

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getIntent() { return intent; }
    public void setIntent(String value) { this.intent = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public ArrayList<PurchaseUnit> getPurchase_units() { return purchase_units; }
    public void setPurchase_units(ArrayList<PurchaseUnit> value) { this.purchase_units = value; }

    public String getTotalValueOfItems() {
        double amount = 0.0;
        String currencyCode = "";
        for (PurchaseUnit purchaseUnit : purchase_units) {
            if (purchaseUnit.getAmount().getBreakdown().existItemTotal()) {
                amount += Double.parseDouble(purchaseUnit.getAmount().getBreakdown().getItem_total().getValue());
                currencyCode = purchaseUnit.getAmount().getBreakdown().getItem_total().getCurrency_code();

            }
        }

        amountOrder = amount;
        this.currencyCode = currencyCode;
        return String.valueOf(amount) + " " + currencyCode;
    }

    public String getTotalShipping(){
        double amount = 0.0;
        String currencyCode = "";
        for (PurchaseUnit purchaseUnit : purchase_units) {
            if (purchaseUnit.getAmount().getBreakdown().existShipping()) {
                amount += Double.parseDouble(purchaseUnit.getAmount().getBreakdown().getShipping().getValue());
                currencyCode = purchaseUnit.getAmount().getBreakdown().getShipping().getCurrency_code();

            }
        }

        amountShipping = amount;
        return String.valueOf(amount)+ " " + currencyCode;
    }

    public String getTotal() {
        double total = 0.0;
        total =  amountOrder + amountShipping;
        return String.valueOf(total) + " " + currencyCode;
    }
}
