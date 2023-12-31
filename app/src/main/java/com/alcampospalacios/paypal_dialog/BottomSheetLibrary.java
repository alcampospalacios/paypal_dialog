package com.alcampospalacios.paypal_dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.alcampospalacios.paypal_dialog.models.PaypalOrder;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BottomSheetLibrary {
    static ExpandableListView expandableListView;
    static ExpandableListAdapter expandableListAdapter;
    static List<String> expandableListTitle;
    static HashMap<String, List<String>> expandableListDetail;

    public static void showBottomSheet(Context context, String orderNumber) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context,  R.style.FullScreenBottomSheetDialog);
        BottomSheetBehavior<View> bottomSheetBehavior;
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, null);
        view.setBackgroundColor(Color.WHITE);

        // Icono de check
        ImageView checkIcon = view.findViewById(R.id.checkIcon);
        checkIcon.setImageResource(R.drawable.ic_circle_check);
        checkIcon.setColorFilter(ContextCompat.getColor(context, R.color.check_color));

        // Texto "Orden completada"
        TextView completedText = view.findViewById(R.id.completedText);
        completedText.setText(context.getString(R.string.order_approved));
        completedText.setTextColor(ContextCompat.getColor(context, R.color.order_complete_color));

        // Texto "Número de orden: xxx"
        TextView orderNumberText = view.findViewById(R.id.orderNumberText);
        orderNumberText.setText(String.format("%s%s", context.getString(R.string.order_number), orderNumber));
        orderNumberText.setTextColor(ContextCompat.getColor(context, R.color.order_number_color));

        // Text order and delivery
        TextView orderText = view.findViewById(R.id.orderText001);
        orderText.setText(context.getString(R.string.order));
        TextView deliveryText = view.findViewById(R.id.deliveryText001);
        deliveryText.setText(context.getString(R.string.delivery));

        // Botones Pagar y Cancelar
        Button payButton = view.findViewById(R.id.payButton);
        payButton.setText(context.getString(R.string.pay_button));
        Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setText(context.getString(R.string.cancel_button));

        // Configurar los eventos de clic o cualquier otra personalización necesaria para los botones
        bottomSheetDialog.setContentView(view);

        // behavior
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        CoordinatorLayout layout = bottomSheetDialog.findViewById(R.id.bottom_sheet_layout);
        assert layout != null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);

//       ************************************** List tile ***********************************
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataItems.getData(context);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(context, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(context,
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(context,
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        context,
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

//        *******************************************

        bottomSheetDialog.show();
    }




}
