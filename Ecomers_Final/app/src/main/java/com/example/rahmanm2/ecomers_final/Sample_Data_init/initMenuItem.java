package com.example.rahmanm2.ecomers_final.Sample_Data_init;

import com.example.rahmanm2.ecomers_final.R;
import com.example.rahmanm2.ecomers_final.SampleDataModel.MainMenuItem;

import java.util.ArrayList;
import java.util.List;

public class initMenuItem {
    public static List<MainMenuItem> mMainMenuItems = new ArrayList<>();
    public static List<MainMenuItem>getMenuItem(){
        int []image = getImage();
        String[]name = getname();
        String[]desc = getDesc();
        String[]price = getPrices();

        for(int i = 0;i<image.length;i++){
            MainMenuItem item = new MainMenuItem();
            item.setImages(image[i]);
            item.setProductName(name[i]);
            item.setProductDescription(desc[i]);
            item.setProductPrice(price[i]);
            mMainMenuItems.add(item);
        }
        return mMainMenuItems;
    }

    private static int []getImage(){

        int []images = {
                R.drawable.ani_cat_five,R.drawable.ani_cat_four
                ,R.drawable.ani_cat_one,R.drawable.ani_cat_four,
                R.drawable.ani_cat_four
        };
        return images;
    }
    private static String[]getname(){
        String []name = {
                "Cat 5","Cat 4","Cat 1","Donats","Button icon"
        };
        return name;
    }
    private static String[]getDesc(){
        String []desc = {
                "beautiful Healthy Cat 5","beautiful Healthy Cat 4",
                "beautiful Healthy Cat 1","Dunkin Donats","button icon to put icon in the file"
        };
        return desc;
    }
    private static String[]getPrices(){
        String[]price = {"150","220","125","3","500"};
        return price;
    }
}
