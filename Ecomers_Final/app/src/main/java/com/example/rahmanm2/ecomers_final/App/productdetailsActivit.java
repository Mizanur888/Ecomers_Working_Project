package com.example.rahmanm2.ecomers_final.App;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahmanm2.ecomers_final.DataBase.Database;
import com.example.rahmanm2.ecomers_final.DataBase.InsertOrderPHP;
import com.example.rahmanm2.ecomers_final.R;
import com.example.rahmanm2.ecomers_final.SampleDataModel.MainMenuItem;
import com.example.rahmanm2.ecomers_final.SampleDataModel.OrderModel;
import com.example.rahmanm2.ecomers_final.config;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class productdetailsActivit extends AppCompatActivity  {

    private Toolbar mToolbar;
    private TextView priceText, NameText,DescText;
    private ImageView mImageView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mBtnChart;
    public static List<OrderModel>Orderlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        setToolbar();
        setupCollapseableToolbar();
        Orderlist = new ArrayList<OrderModel>();

        mBtnChart = (FloatingActionButton)findViewById(R.id.btnCart);
        priceText = (TextView)findViewById(R.id.txtProductPrice);
        NameText = (TextView)findViewById(R.id.txtProductName);
        DescText = (TextView)findViewById(R.id.txtProductDescriptionID);
        mImageView = (ImageView)findViewById(R.id.menuImage);

        final Intent pdIntent = getIntent();
        MainMenuItem menuDetails = (MainMenuItem)pdIntent.getSerializableExtra(config.PRODUCT_DETAILS);
        if(menuDetails == null){
            menuDetails = new MainMenuItem();
        }

        priceText.setText(menuDetails.getProductPrice());
        NameText.setText(menuDetails.getProductName());
        DescText.setText(menuDetails.getProductDescription());
        Picasso.with(this)
                .load(menuDetails.getImages())
                .fit()
                .centerCrop()
                .into(mImageView);
       mCollapsingToolbarLayout.setTitle(menuDetails.getProductName());

        Log.d("items",menuDetails.getProductName()+"|"+menuDetails.getProductDescription()
                +" | "+menuDetails.getProductPrice());
        AddtoCart();
    }
    private void AddtoCart(){
        mBtnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               OrderModel model = new OrderModel("12",NameText.getText().toString(),DescText.getText().toString()
                       ,"3",
                       priceText.getText().toString());
               //operasi=insert&ProductID=22&ProductName=Cat&ProductDesc=verycat&Quantity=3&ProductPrice=9090

               Orderlist.add(model);
               String url = config.ROOT_URL+"operasi=insert&ProductID="+model.getProductID()+"&ProductName="+
                          model.getProductName()+"&ProductDesc="+model.getProductDesc()+"&Quantity="+model.getQuantity()
                          +"&ProductPrice="+model.getProductprice();

                InsertOrderPHP.InsertOrder(url,model,productdetailsActivit.this);
                Toast.makeText(productdetailsActivit.this,"Added To Cart",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void setToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.menuProduct);
        setSupportActionBar(mToolbar);
    }

    private void setupCollapseableToolbar(){
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collpsingToolbar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpanedAppbar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

    }
}


              /* new Database(getBaseContext()).AddTocart(new OrderModel(
                        "1",
                        NameText.getText().toString(),
                        DescText.getText().toString(),
                        "2",
                        priceText.getText().toString()
      ProductID = productID;
        ProductName = productName;
        ProductDesc = productDesc;
        Quantity = quantity;
        Productprice = productprice;
                ));*/