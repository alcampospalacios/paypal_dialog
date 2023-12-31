package com.alcampospalacios.paypal_dialog.models;

public class PurchaseUnit {
    private String referenceID;
    private Amount amount;
    private Payee payee;
    private Item[] items;

    public String getReferenceID() { return referenceID; }
    public void setReferenceID(String value) { this.referenceID = value; }

    public Amount getAmount() { return amount; }
    public void setAmount(Amount value) { this.amount = value; }

    public Payee getPayee() { return payee; }
    public void setPayee(Payee value) { this.payee = value; }

    public Item[] getItems() { return items; }
    public void setItems(Item[] value) { this.items = value; }
}
