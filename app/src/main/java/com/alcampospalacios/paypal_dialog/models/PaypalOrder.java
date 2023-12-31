package com.alcampospalacios.paypal_dialog.models;

import java.time.OffsetDateTime;

public class PaypalOrder {
    private String id;
    private String intent;
    private String status;
    private PurchaseUnit[] purchaseUnits;
    private OffsetDateTime createTime;
    private Link[] links;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getIntent() { return intent; }
    public void setIntent(String value) { this.intent = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public PurchaseUnit[] getPurchaseUnits() { return purchaseUnits; }
    public void setPurchaseUnits(PurchaseUnit[] value) { this.purchaseUnits = value; }

    public OffsetDateTime getCreateTime() { return createTime; }
    public void setCreateTime(OffsetDateTime value) { this.createTime = value; }

    public Link[] getLinks() { return links; }
    public void setLinks(Link[] value) { this.links = value; }
}
