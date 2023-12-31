package com.alcampospalacios.paypal_dialog.models;

public class Payee {
    private String emailAddress;
    private String merchantID;

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String value) { this.emailAddress = value; }

    public String getMerchantID() { return merchantID; }
    public void setMerchantID(String value) { this.merchantID = value; }
}
