package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

public class Breakdown {
    private ItemTotal item_total;
    private ItemTotal shipping;
    private ItemTotal tax_total;

    public Breakdown(){}

    public Breakdown(
            @NonNull ItemTotal item_total,
            ItemTotal shipping,
            ItemTotal tax_total
    ) {
        this.item_total = item_total;
        this.shipping = shipping;
        this.tax_total = tax_total;
    };

    public ItemTotal getItem_total() { return item_total; }
    public void setItem_total(ItemTotal value) { this.item_total = value; }

    public ItemTotal getShipping() { return shipping; }
    public void setShipping(ItemTotal value) { this.shipping = value; }

    public Boolean existShipping() {
        return shipping != null;
    }

    public Boolean existItemTotal() {
        return item_total != null;
    }

    public ItemTotal getTax_total() { return tax_total; }
    public void setTax_total(ItemTotal value) { this.tax_total = value; }
}
