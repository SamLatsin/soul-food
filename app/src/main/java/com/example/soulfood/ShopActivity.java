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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private ArrayList<DishItem> dishList;
    private RecyclerView mRecyclerView;
    private DishAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int final_price = 0;
    private int final_count = 0;

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

        String current_restaurant = "Choose your restaurant";
        ((GlobalOrder) this.getApplication()).setCurrentRestaurant("Choose your restaurant");
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
                        test(items[which].toString());
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
        ImageButton basket_button = (ImageButton)findViewById(R.id.basketButton);
        basket_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });

    }

    public void test(String restaurant) {
        ((GlobalOrder) this.getApplication()).setCurrentRestaurant(restaurant);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        dishList.get(data.getIntExtra("dishId", 0)).setDishCount(data.getIntExtra("itemCount", 0));
        final_count = 0;
        final_price = 0;
        for(int position = 0; position<dishList.size(); position++) {
            dishList.get(position).getDishPrice();
            dishList.get(position).getDishCount();
            final_price += dishList.get(position).getDishCount() * dishList.get(position).getDishPrice();
            final_count += dishList.get(position).getDishCount();
        }
        TextView  tv = (TextView )findViewById(R.id.textView6);
        tv.setText(Integer.toString(final_count) +" items    $ " + Integer.toString(final_price) + ".00");
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
                Intent intent = new Intent(ShopActivity.this, ItemActivity.class);
                intent.putExtra("dishId", position);
                intent.putExtra("dishItem", dishList.get(position));
                startActivityForResult(intent, 1);
            }

            @Override
            public void onButtonClick(int position) {
                final_count += 1;
                dishList.get(position).setDishCount(final_count);
                final_price += dishList.get(position).getDishPrice();
                TextView  tv = (TextView )findViewById(R.id.textView6);
                tv.setText(Integer.toString(final_count) +" items    $ " + Integer.toString(final_price) + ".00");
            }
        });
    }
}