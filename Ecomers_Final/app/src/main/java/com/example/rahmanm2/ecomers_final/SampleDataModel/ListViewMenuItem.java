package com.example.rahmanm2.ecomers_final.SampleDataModel;

import android.media.Image;

public class ListViewMenuItem{
    private String Description;
    private String Discount;
    private String Image;
    private String MenuId;
    private String Name;
    private String Price;

    public ListViewMenuItem() {
    }

    public ListViewMenuItem(String description, String discount, String image, String menuId, String name, String price) {
        Description = description;
        Discount = discount;
        Image = image;
        MenuId = menuId;
        Name = name;
        Price = price;
    }

    public ListViewMenuItem(String description, String image, String menuId, String name, String price) {
        Description = description;
        Image = image;
        MenuId = menuId;
        Name = name;
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
