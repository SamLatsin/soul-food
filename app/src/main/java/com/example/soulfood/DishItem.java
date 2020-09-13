package com.example.soulfood;

import java.io.Serializable;

public class DishItem implements Serializable {
    private String mDishName;
    private String mDishDesc;
    private int mDishPrice;
    private int mPhotoId;
    private int mDishCount = 0;
    public DishItem(String dishName, String dishDesc, int dishPrice, int photoId) {
        mDishName = dishName;
        mDishDesc = dishDesc;
        mDishPrice = dishPrice;
        mPhotoId = photoId;
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
    public int getDishCount(){
        return mDishCount;
    }
    public void setDishCount(int DishCount) {
        this.mDishCount = DishCount;
    }
}
