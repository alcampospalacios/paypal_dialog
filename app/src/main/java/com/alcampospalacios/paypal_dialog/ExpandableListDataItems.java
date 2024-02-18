package com.alcampospalacios.paypal_dialog;

import android.content.Context;
import android.util.Log;

import com.alcampospalacios.paypal_dialog.models.PurchaseUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData(Context context,ArrayList<PurchaseUnit> purchaseUnits) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<String> items = new ArrayList<String>();


        for (int i = 0; i < purchaseUnits.size(); i++) {
            for (int i1 = 0; i1 < purchaseUnits.get(i).getItems().size(); i1++) {
                items.add(purchaseUnits.get(i).getItems().get(i1).getName());
            }

        }




        expandableListDetail.put(context.getString(R.string.ordered_items), items);
        return expandableListDetail;
    }
}
