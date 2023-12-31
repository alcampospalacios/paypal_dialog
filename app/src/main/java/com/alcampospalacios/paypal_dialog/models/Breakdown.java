package com.alcampospalacios.paypal_dialog.models;

public class Breakdown {
    private ItemTotal itemTotal;
    private ItemTotal shipping;


    public ItemTotal getItemTotal() { return itemTotal; }
    public void setItemTotal(ItemTotal value) { this.itemTotal = value; }

    public ItemTotal getShipping() { return shipping; }
    public void setShipping(ItemTotal value) { this.shipping = value; }

    public Boolean existShipping() {
        return shipping != null;
    }

    public Boolean existItemTotal() {
        return itemTotal != null;
    }
}
