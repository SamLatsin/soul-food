package com.example.soulfood;

public class DishItem {
    private String mDishName;
    private String mDishDesc;
    private int mDishPrice;
    private int mPhotoId;
    public DishItem(String dishName, String dishDesc, int dishPrice, int photoId) {
        mDishName = dishName;
        mDishDesc = dishDesc;
        mDishPrice = dishPrice;
        mPhotoId = photoId;;
    }
    public String getDishName(){
        return mDishName;
    }
    public String getDishDesc(){
        return mDishDesc;
    }
    public int getDishPrice(){
        return mDishPrice;
    }
    public int getPhotoId(){
        return mPhotoId;
    }
}
