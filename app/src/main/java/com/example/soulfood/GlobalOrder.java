package com.example.soulfood;

import android.app.Application;

public class GlobalOrder extends Application {

    private String currentRestaurant;
    private DishItem dishItem;


    public String getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(String currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public DishItem getDishItem() {
        return dishItem;
    }

    public void setDishItem(DishItem dishItem) {
        this.dishItem = dishItem;
    }
}
