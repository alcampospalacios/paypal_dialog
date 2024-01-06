package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

public class Breakdown {
    private ItemTotal item_total;
    private ItemTotal shipping;

    public Breakdown(
            @NonNull ItemTotal item_total,
            ItemTotal shipping
    ) {
        this.item_total = item_total;
        this.shipping = shipping;
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
}
