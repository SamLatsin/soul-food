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
    int item_full_price;
    int item_count = 1;
    boolean fav_state = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        final DishItem dishItem = (DishItem)getIntent().getSerializableExtra("dishItem");
        setItemProp(dishItem);

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
                item_full_price += dishItem.getDishPrice();
                item_count += 1;
                item_count_text.setText(Integer.toString(item_count));
                item_full_price_text.setText("$" + Integer.toString(item_full_price));
            }
        });
        Button minus_button = (Button)findViewById(R.id.minusButton);
        minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item_count_text.getText().toString().equals("0")) {
                    item_full_price -= dishItem.getDishPrice();
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
        final Button add_to_order = (Button)findViewById(R.id.orderButton);
        add_to_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishItem.setDishCount(item_count);
                Intent intent = new Intent(ItemActivity.this, ShopActivity.class);
                intent.putExtra("itemCount", item_count);

                intent.putExtra("dishId", getIntent().getExtras().getInt("dishId"));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void setItemProp(DishItem dishItem) {
        TextView tv1 = (TextView)findViewById(R.id.textView7);
        TextView tv2 = (TextView)findViewById(R.id.textView8);
        TextView tv3 = (TextView)findViewById(R.id.fullItemPrice);
        TextView item_count_text = (TextView)findViewById(R.id.currentItemCount);
        ImageView img1 = (ImageView)findViewById(R.id.imageView4);
        item_count_text.setText(Integer.toString(dishItem.getDishCount()));
        item_count = dishItem.getDishCount();
        item_full_price += item_count * dishItem.getDishPrice();
        tv1.setText(dishItem.getDishName());
        tv2.setText(dishItem.getDishDesc());
        tv3.setText("$" + Integer.toString(item_full_price));
        img1.setImageResource(dishItem.getPhotoId());
    }
}