package com.example.soulfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {
    String dishName = "";
    String dishDesc = "";
    int dishPrice = 0;
    int dishPhotoId = 0;
    int item_count = 1;
    int item_full_price;
    boolean fav_state = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setItemProp();

        final TextView item_count_text = (TextView)findViewById(R.id.currentItemCount);
        final TextView item_full_price_text = (TextView)findViewById(R.id.fullItemPrice);
        Button back_button = (Button)findViewById(R.id.backButton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button plus_button = (Button)findViewById(R.id.plusButton);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_full_price += dishPrice;
                item_count += 1;
                item_count_text.setText(Integer.toString(item_count));
                item_full_price_text.setText("$" + Integer.toString(item_full_price));
            }
        });
        Button minus_button = (Button)findViewById(R.id.minusButton);
        minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item_count_text.getText().toString().equals("1")) {
                    item_full_price -= dishPrice;
                    item_count -= 1;
                    item_count_text.setText(Integer.toString(item_count));
                    item_full_price_text.setText("$" + Integer.toString(item_full_price));
                }
            }
        });
        final ImageView fav_button = (ImageView)findViewById(R.id.favButton);
        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fav_state) {
                    fav_button.setImageResource(R.drawable.i_fav_full);
                    fav_state = false;
                }
                else {
                    fav_button.setImageResource(R.drawable.i_fav_empty);
                    fav_state = true;
                }
            }
        });
    }

    public void setItemProp() {


        dishName = getIntent().getExtras().getString("dishName");
        dishDesc = getIntent().getExtras().getString("dishDesc");
        dishPrice = getIntent().getExtras().getInt("dishPrice");
        dishPhotoId = getIntent().getExtras().getInt("dishPhotoId");
        TextView tv1 = (TextView)findViewById(R.id.textView7);
        TextView tv2 = (TextView)findViewById(R.id.textView8);
        TextView tv3 = (TextView)findViewById(R.id.fullItemPrice);
        item_full_price += dishPrice;
        ImageView img1 = (ImageView)findViewById(R.id.imageView4);
        tv1.setText(dishName);
        tv2.setText(dishDesc);
        tv3.setText("$" + Integer.toString(dishPrice));
        img1.setImageResource(dishPhotoId);
    }
}