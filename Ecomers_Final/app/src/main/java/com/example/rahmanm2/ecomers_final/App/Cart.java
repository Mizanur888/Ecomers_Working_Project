package com.example.rahmanm2.ecomers_final.App;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.rahmanm2.ecomers_final.R;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView txtTotal;
    private FButton btnPlaceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        //init recycler view
        mRecyclerview = (RecyclerView)findViewById(R.id.OrderListInCart);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);

        txtTotal = (TextView)findViewById(R.id.txtTotal);
        btnPlaceOrder = (FButton)findViewById(R.id.btnPlaceOrder);

    }
}
