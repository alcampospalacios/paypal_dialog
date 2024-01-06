package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

public class Item {
    private String name;
    private ItemTotal unit_amount;
    private String quantity;
    private String description;

    public Item(
            @NonNull String name,
            @NonNull ItemTotal unit_amount,
            @NonNull String quantity,
            @NonNull String description
    ) {
        this.name = name;
        this.unit_amount = unit_amount;
        this.quantity = quantity;
        this.description = description;
    };

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public ItemTotal getUnit_amount() { return unit_amount; }
    public void setUnit_amount(ItemTotal value) { this.unit_amount = value; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String value) { this.quantity = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }
}
