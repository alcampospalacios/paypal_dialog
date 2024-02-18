package com.alcampospalacios.paypal_dialog.models;

import androidx.annotation.NonNull;

public class Link {
    private String href;
    private String rel;
    private String method;

    public Link(
            @NonNull String href,
            @NonNull String rel,
            @NonNull String method
    ) {
        this.href = href;
        this.rel = rel;
        this.method = method;
    };

    public String getHref() { return href; }
    public void setHref(String value) { this.href = value; }

    public String getRel() { return rel; }
    public void setRel(String value) { this.rel = value; }

    public String getMethod() { return method; }
    public void setMethod(String value) { this.method = value; }
}
