package com.example.rahmanm2.ecomers_final.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rahmanm2.ecomers_final.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionFragment extends android.support.v4.app.Fragment {


    public AuctionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auction, container, false);
    }

}
