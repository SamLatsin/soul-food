package com.example.soulfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        String restaurant_name = "";
        restaurant_name = getIntent().getExtras().getString("restaurant");
        TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setText(restaurant_name);

        LinearLayout menu_photos = (LinearLayout )findViewById(R.id.linearLayout1);
        menu_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ShopActivity.this, R.style.MyAlertDialogStyle);
                final CharSequence[] items = new CharSequence[] {"Dinner in the Sky", "Parallax Restaurant", "El Diablo “The Devil”", "Signs", "Norma’s", "The Disaster Café", "Ninja Dining", "Aurum", "Forbes Island", "Rogo’s", "RAW Canvas"};

                adb.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView)findViewById(R.id.textView1);
                        tv.setText(items[which]);
                        tv.setTextColor(Color.BLACK);
                    }
                });
                adb.setTitle("Choose your restaurant");
                adb.show();
            }
        });
        ArrayList<DishItem> dishList = new ArrayList<>();
        dishList.add(new DishItem("French Toasts", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 12, R.drawable.im_food_01));
        dishList.add(new DishItem("Avocado & Egg Toast", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 16, R.drawable.im_food_02));
        dishList.add(new DishItem("Fruits Plate", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 19, R.drawable.im_food_03));

//        mRecyclerView = findViewById(R.id.rv);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mAdapter = new DishAdapter(dishList);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);


    }
}