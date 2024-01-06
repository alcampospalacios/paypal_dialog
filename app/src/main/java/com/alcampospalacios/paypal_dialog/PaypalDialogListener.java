package com.alcampospalacios.paypal_dialog;

public interface PaypalDialogListener {
    void onSuccessCapture(String data);
    void onErrorCapture(String data);
    void onCancelPayOrder();
}
