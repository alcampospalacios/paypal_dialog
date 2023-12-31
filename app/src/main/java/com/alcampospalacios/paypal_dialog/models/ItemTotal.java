package com.alcampospalacios.paypal_dialog.models;

public class ItemTotal {
    private String currencyCode;
    private String value;

    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String value) { this.currencyCode = value; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
