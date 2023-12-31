package com.alcampospalacios.paypal_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alcampospalacios.paypal_dialog.models.PaypalOrder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomSheetLibrary.showBottomSheet(MainActivity.this,  "16C16971H8286605S"

                );

    }
}