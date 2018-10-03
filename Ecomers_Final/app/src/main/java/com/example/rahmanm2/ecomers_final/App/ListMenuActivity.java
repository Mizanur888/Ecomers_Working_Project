package com.example.rahmanm2.ecomers_final.App;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.rahmanm2.ecomers_final.Adapter.ListMenuAdapter;
import com.example.rahmanm2.ecomers_final.FirebaseUI.FirebaseUI;
import com.example.rahmanm2.ecomers_final.R;
import com.example.rahmanm2.ecomers_final.SampleDataModel.ListViewMenuItem;
import com.example.rahmanm2.ecomers_final.SampleDataModel.MainMenuItem;
import com.example.rahmanm2.ecomers_final.config;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class ListMenuActivity extends AppCompatActivity {

    MaterialSearchBar mMaterialSearchBar;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    String listItemID = "";
    List<ListViewMenuItem>mListViewMenuItems;
    List<String>SuggesteSearch;
    ListMenuAdapter adapter;
    ListMenuAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        mListViewMenuItems = new ArrayList<ListViewMenuItem>();
        FirebaseUI.DatabaseConnection("Foods");
        mFirebaseDatabase = FirebaseUI.mFirebaseDatabase;
        mDatabaseReference = FirebaseUI.mDataBaseReference;

        //search functionality
        SuggesteSearch = new ArrayList<>();
        mMaterialSearchBar = (MaterialSearchBar)findViewById(R.id.MenuSearchBarID);

        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclermenuItemsID);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager manager1 = new GridLayoutManager(this,2);
         mRecyclerView.setLayoutManager(manager1);


        if(getIntent()!=null){
            listItemID = getIntent().getStringExtra(config.PRODUCT_DETAILS);
        }
        if(!listItemID.isEmpty() && listItemID!=null){
            loadProdcutList(listItemID);
        }

        mMaterialSearchBar.setHint("Enter your Product Name");
        LoadSuggest();
        mMaterialSearchBar.setLastSuggestions(SuggesteSearch);
        mMaterialSearchBar.setCardViewElevation(10);
        mMaterialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String>suggest = new ArrayList<>();
                for(String search:SuggesteSearch){
                    if(search.toLowerCase().contains(mMaterialSearchBar.getText().toLowerCase()))
                        suggest.add(search);

                }
                mMaterialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mMaterialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                //when search bar is closed
                //restore orgnal adapter
                if(!enabled)
                   mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                //when search finished show result of search adapter
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void startSearch(CharSequence text) {
        final List<ListViewMenuItem>items = new ArrayList<>();
        mDatabaseReference.orderByChild("Name").equalTo(text.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    ListViewMenuItem itms = data.getValue(ListViewMenuItem.class);
                    items.add(itms);
                }
                searchAdapter = new ListMenuAdapter(getApplicationContext(), items);
                mRecyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void LoadSuggest() {
        mDatabaseReference.orderByChild("MenuId").equalTo(listItemID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    ListViewMenuItem itms = data.getValue(ListViewMenuItem.class);
                    SuggesteSearch.add(itms.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadProdcutList(String listItemID) {
         mDatabaseReference.orderByChild("MenuId").equalTo(listItemID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    ListViewMenuItem itms = data.getValue(ListViewMenuItem.class);
                    mListViewMenuItems.add(itms);
                }
                adapter = new ListMenuAdapter(getApplicationContext(), mListViewMenuItems);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
