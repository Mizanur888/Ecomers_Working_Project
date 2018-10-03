package com.example.rahmanm2.ecomers_final.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahmanm2.ecomers_final.R;
import com.example.rahmanm2.ecomers_final.SampleDataModel.ListViewMenuItem;
import com.example.rahmanm2.ecomers_final.SampleDataModel.MainMenuItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.listViewHolder>{

    List<ListViewMenuItem>mListViewMenuItems;
    LayoutInflater mLayoutInflater;
    Context mContext;
    public ListMenuAdapter(Context context, List<ListViewMenuItem>mListViewMenuItems){
        mContext = context;
        this.mListViewMenuItems = mListViewMenuItems;
        //mLayoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_menu_items,parent,false);
        listViewHolder holder = new listViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {

        ListViewMenuItem currentItems = mListViewMenuItems.get(position);
        Picasso.with(mContext)
                .load(currentItems.getImage())
                .fit()
                .centerCrop()
                .into(holder.List_menu_image_itemID);
        holder.List_menu_item_name.setText(currentItems.getName());
        holder.List_menu_item_descripID.setText(currentItems.getDescription());
        holder.list_menu_item_PriceID.setText(currentItems.getPrice());
    }

    @Override
    public int getItemCount() {
        return mListViewMenuItems.size();
    }

    public class listViewHolder extends RecyclerView.ViewHolder{

            ImageView List_menu_image_itemID;
            TextView List_menu_item_name;
            TextView List_menu_item_descripID;
            TextView list_menu_item_PriceID;

         public listViewHolder(View itemView) {
            super(itemView);
            List_menu_image_itemID = (ImageView) itemView.findViewById(R.id.List_menu_image_itemID);
            List_menu_item_name = (TextView) itemView.findViewById(R.id.List_menu_item_name);
            List_menu_item_descripID = (TextView) itemView.findViewById(R.id.List_menu_item_descripID);
            list_menu_item_PriceID = (TextView) itemView.findViewById(R.id.list_menu_item_PriceID);
         }

         public void setItemData (ListViewMenuItem model){
            // int val = Integer.parseInt(model.getImage());

           // List_menu_image_itemID.setImageResource(val);

         }
    }

}
