package com.alcampospalacios.paypal_dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.alcampospalacios.paypal_dialog.api.ICaptureOrderApi;
import com.alcampospalacios.paypal_dialog.models.ErrorInterceptor;
import com.alcampospalacios.paypal_dialog.models.PaypalOrder;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BottomSheetLibrary {
    static ExpandableListView expandableListView;
    static ExpandableListAdapter expandableListAdapter;
    static List<String> expandableListTitle;
    static HashMap<String, List<String>> expandableListDetail;

    // Listeners
    static PaypalDialogListener listener;

    public static void showBottomSheet(
            @NonNull PaypalDialogListener paypalDialogListener,
            @NonNull Context context,
            @NonNull String orderId,
            @NonNull PaypalOrder paypalOrder,
            @NonNull String accessToken,
            @NonNull String paypalRequestId,
            @NonNull String url
            ) {
        listener = paypalDialogListener;

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
        orderNumberText.setText(String.format("%s%s", context.getString(R.string.order_number), orderId));
        orderNumberText.setTextColor(ContextCompat.getColor(context, R.color.order_number_color));

        // Text order and delivery
        TextView orderText = view.findViewById(R.id.orderText001);
        orderText.setText(context.getString(R.string.order));

        TextView orderValueText = view.findViewById(R.id.orderValue001);
        orderValueText.setText(paypalOrder.getTotalValueOfItems());

        TextView deliveryText = view.findViewById(R.id.deliveryText001);
        deliveryText.setText(context.getString(R.string.delivery));

        TextView deliveryValueText = view.findViewById(R.id.deliveryTextValue001);
        deliveryValueText.setText(paypalOrder.getTotalShipping());

        TextView summaryText = view.findViewById(R.id.summaryValue001);
        summaryText.setText(paypalOrder.getTotal());

        // Botones Pagar y Cancelar
        Button payButton = view.findViewById(R.id.payButton);
        payButton.setText(context.getString(R.string.pay_button));
        Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setText(context.getString(R.string.cancel_button));


        LinearLayout layoutOfButtons = (LinearLayout) view.findViewById(R.id.layoutOfButtons001);

        LinearLayout layoutOfLoading = (LinearLayout) view.findViewById(R.id.layoutOfLoading001);
        layoutOfLoading.setVisibility(View.GONE);


        // Set an OnClickListener for the payButton
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to be executed when the button is clicked

                captureMoney(
                        accessToken,
                        paypalRequestId,
                        orderId,
                        url,
                        layoutOfButtons,
                        layoutOfLoading
                );
            }
        });


        // Set an OnClickListener for the cancelButton
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCancelPayOrder();
            }
        });



        bottomSheetDialog.setContentView(view);

        // behavior
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        CoordinatorLayout layout = bottomSheetDialog.findViewById(R.id.bottom_sheet_layout);
        assert layout != null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);

//       ************************************** List tile ***********************************
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataItems.getData(context, paypalOrder.getPurchase_units());
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(context, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(context,
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(context,
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        *******************************************

        bottomSheetDialog.show();
    }



    static private void captureMoney(
            @NonNull String accessToken,
            @NonNull String paypalRequestId ,
            @NonNull String orderId,
            @NonNull String url,
            @NonNull LinearLayout layoutOfButtons,
            @NonNull LinearLayout layoutOfLoading
    ) {

        layoutOfButtons.setVisibility(View.GONE);
        layoutOfLoading.setVisibility(View.VISIBLE);

        // Instance to log the url and data retrofit
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // Modify request to add headers and see logs
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", "Bearer " + accessToken)
                                .header("PayPal-Request-Id", paypalRequestId)
                                .header("Content-Type", "application/json")
                                .method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(interceptor)
                .build();

        // Building retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Building and instance of interface api to make the capture
        ICaptureOrderApi apiService = retrofit.create(ICaptureOrderApi.class);

        // Doing the request to capture the money
        Call<Void> retrofitCall = apiService.captureOrder(orderId);

        retrofitCall.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    layoutOfButtons.setVisibility(View.VISIBLE);
                    layoutOfLoading.setVisibility(View.GONE);

//                    listener.onSuccessCapture("success");

                } else {
                    layoutOfButtons.setVisibility(View.VISIBLE);
                    layoutOfLoading.setVisibility(View.GONE);


                    Gson gson = new Gson();
                    ErrorInterceptor message = gson.fromJson(response.errorBody().charStream(), ErrorInterceptor.class);
                    Log.d("onResponse", message.getMessage());
//                    listener.onErrorCapture("error");
                }


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                layoutOfButtons.setVisibility(View.VISIBLE);
                layoutOfLoading.setVisibility(View.GONE);


                Log.d("onFailure", t.getMessage());
//                    result.error("error", t.getMessage(), t.getMessage());
                listener.onErrorCapture(t.getMessage());

            }
        });

    }





}
