package com.alcampospalacios.paypal_dialog.models;

public class Amount {
    private String currencyCode;
    private String value;
    private Breakdown breakdown;

    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String value) { this.currencyCode = value; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public Breakdown getBreakdown() { return breakdown; }
    public void setBreakdown(Breakdown value) { this.breakdown = value; }
}
