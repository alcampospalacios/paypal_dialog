package com.alcampospalacios.paypal_dialog.models;

public class Item {
    private String name;
    private ItemTotal unitAmount;
    private String quantity;
    private String description;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public ItemTotal getUnitAmount() { return unitAmount; }
    public void setUnitAmount(ItemTotal value) { this.unitAmount = value; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String value) { this.quantity = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }
}
