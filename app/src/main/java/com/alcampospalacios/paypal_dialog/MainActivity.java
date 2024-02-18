package com.alcampospalacios.paypal_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alcampospalacios.paypal_dialog.models.Amount;
import com.alcampospalacios.paypal_dialog.models.Breakdown;
import com.alcampospalacios.paypal_dialog.models.Item;
import com.alcampospalacios.paypal_dialog.models.ItemTotal;
import com.alcampospalacios.paypal_dialog.models.Payee;
import com.alcampospalacios.paypal_dialog.models.PaypalOrder;
import com.alcampospalacios.paypal_dialog.models.PurchaseUnit;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To test library
        Breakdown breakdown = new Breakdown(new ItemTotal("USD", "40.00"),
                new ItemTotal("USD", "10.00"), new ItemTotal("USD", "10.00"));
        Amount amount = new Amount(
                "USD",
                "50.00",
                breakdown
        );
        Payee payee = new Payee("sb-yawlm28407032@business.example.com", "GT25NVQDX7R96");
        Item item1 = new Item(
                "T-Shirt",
                new ItemTotal("USD", "10.00"),
                "1",
                "Green XL");
        Item item2 = new Item(
                "Dress",
                new ItemTotal("USD", "30.00"),
                "1",
                "Green s");
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item1);
        items.add(item2);

        PurchaseUnit purchaseUnits1  = new PurchaseUnit("default", amount, payee, items);
        ArrayList<PurchaseUnit> purchaseUnits = new ArrayList<PurchaseUnit>();
        purchaseUnits.add(purchaseUnits1);



        PayPalCallBackDialogHelper listener =  new PayPalCallBackDialogHelper();
        PaypalOrder paypalOrder = new PaypalOrder(
            "2H613476YD661370J",
                "CAPTURE",
                "CREATED",
                purchaseUnits

        );

        BottomSheetLibrary.showBottomSheet(
                listener,
                MainActivity.this,
                "16C16971H8286605S",
                paypalOrder,
                "A21AALEI-HWljELAzRoWincgz8w6hv2zCgShfkKZb5irmAHk4a_7U-7oogdxjevUrMZ1mD1wwnpJFFHxlc2eEa_Eo3HBfm2GQ",
                "82323fde-c0db-4ab0-a603-db9df53dd0cc",
                "https://api-m.sandbox.paypal.com"


                );

    }
}