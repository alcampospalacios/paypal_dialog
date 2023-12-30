package com.alcampospalacios.paypal_dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("T-Shirt");
        cricket.add("Dress with two colors");
        cricket.add("Watch");

        expandableListDetail.put("Ordered Items", cricket);
        return expandableListDetail;
    }
}
