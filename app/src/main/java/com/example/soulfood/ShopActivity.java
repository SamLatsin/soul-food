package com.example.soulfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private ArrayList<DishItem> dishList;
    private RecyclerView mRecyclerView;
    private DishAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int resultPrice = 0;
    private int selectedItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        createExampleList();
        buildRecyclerView();

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

        final TextView  tv1 = (TextView )findViewById(R.id.textView2);
        final TextView  tv2 = (TextView )findViewById(R.id.textView3);
        final TextView  tv3 = (TextView )findViewById(R.id.textView4);
        final TextView  tv4 = (TextView )findViewById(R.id.textView5);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.parseColor("#F0821A"));
                tv2.setTextColor(Color.parseColor("#504D4B"));
                tv3.setTextColor(Color.parseColor("#504D4B"));
                tv4.setTextColor(Color.parseColor("#504D4B"));
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.parseColor("#504D4B"));
                tv2.setTextColor(Color.parseColor("#F0821A"));
                tv3.setTextColor(Color.parseColor("#504D4B"));
                tv4.setTextColor(Color.parseColor("#504D4B"));
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.parseColor("#504D4B"));
                tv2.setTextColor(Color.parseColor("#504D4B"));
                tv3.setTextColor(Color.parseColor("#F0821A"));
                tv4.setTextColor(Color.parseColor("#504D4B"));
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.parseColor("#504D4B"));
                tv2.setTextColor(Color.parseColor("#504D4B"));
                tv3.setTextColor(Color.parseColor("#504D4B"));
                tv4.setTextColor(Color.parseColor("#F0821A"));
            }
        });


    }

    public void createExampleList() {
        dishList = new ArrayList<>();
        dishList.add(new DishItem("French Toasts", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 12, R.drawable.im_food_01));
        dishList.add(new DishItem("Avocado & Egg Toast", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 16, R.drawable.im_food_02));
        dishList.add(new DishItem("Fruits Plate", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 19, R.drawable.im_food_03));
        dishList.add(new DishItem("Chef's breakfaast", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 22, R.drawable.im_food_04));
        dishList.add(new DishItem("French Toasts", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 12, R.drawable.im_food_01));
        dishList.add(new DishItem("Avocado & Egg Toast", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 16, R.drawable.im_food_02));
        dishList.add(new DishItem("Fruits Plate", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 19, R.drawable.im_food_03));
        dishList.add(new DishItem("Chef's breakfaast", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temp", 22, R.drawable.im_food_04));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DishAdapter(dishList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new DishAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("cardview", Integer.toString(position));
                Intent intent = new Intent(ShopActivity.this, ItemActivity.class);
                intent.putExtra("dishName", dishList.get(position).getDishName());
                intent.putExtra("dishDesc", dishList.get(position).getDishDesc());
                intent.putExtra("dishPrice", dishList.get(position).getDishPrice());
                intent.putExtra("dishPhotoId", dishList.get(position).getPhotoId());
                startActivity(intent);
//                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
//                Integer.toString(dishList.get(position))
            }

            @Override
            public void onButtonClick(int position) {
                Log.d("button", Integer.toString(position));
                selectedItems += 1;
                resultPrice += dishList.get(position).getDishPrice();
                Log.d("price", Integer.toString(resultPrice));
                TextView  tv = (TextView )findViewById(R.id.textView6);
                tv.setText(Integer.toString(selectedItems) +" items    $ " + Integer.toString(resultPrice) + ".00");
            }
        });
    }
}