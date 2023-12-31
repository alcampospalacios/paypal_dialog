package com.alcampospalacios.paypal_dialog;

import android.content.Context;

import com.alcampospalacios.paypal_dialog.models.PurchaseUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData(Context context, PurchaseUnit[] purchaseUnits) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<String> items = new ArrayList<String>();

        for (int i = 0; i < purchaseUnits.length; i++) {
            for (int i1 = 0; i1 < purchaseUnits[i].getItems().length; i1++) {
                items.add(purchaseUnits[i].getItems()[i1].getName());
            }

        }




        expandableListDetail.put(context.getString(R.string.ordered_items), items);
        return expandableListDetail;
    }
}
