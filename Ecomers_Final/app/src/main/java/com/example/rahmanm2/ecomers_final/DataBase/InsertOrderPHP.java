package com.example.rahmanm2.ecomers_final.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rahmanm2.ecomers_final.App.productdetailsActivit;
import com.example.rahmanm2.ecomers_final.SampleDataModel.OrderModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertOrderPHP {
    static ProgressDialog mProgressDialog;
    public static void InsertOrder(String url, final OrderModel model, final Context context){

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Please Wait...............");
        mProgressDialog.show();

        StringRequest stringREQ = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressDialog.dismiss();
                        Toast.makeText(context,"Order Successful",Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                Toast.makeText(context,"Order Unsuccessful",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parms = new HashMap<>();
                parms.put("ProductID",model.getProductID());
                parms.put("ProductName",model.getProductName());
                parms.put("ProductDesc",model.getProductDesc());
                parms.put("Quantity",model.getQuantity());
                parms.put("ProductPrice",model.getProductprice());

                return parms;
            }
        };
        Volley.newRequestQueue(context).add(stringREQ);
    }

    public List<OrderModel>getOrder(String url, Context context){
        List<OrderModel>model= new ArrayList<OrderModel>();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Please Wait...............");
        mProgressDialog.show();


        return model;
    }
}
