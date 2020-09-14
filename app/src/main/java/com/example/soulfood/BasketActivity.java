package com.example.soulfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    private ArrayList<DishItem> dishList;
    private ArrayList<DishItem> dishOrder;
    private RecyclerView mRecyclerView;
    private OrderAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        Button back_button = (Button)findViewById(R.id.backButton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dishList = (ArrayList<DishItem>) getIntent().getSerializableExtra("dishList");
        dishOrder = new ArrayList<>();
        
        for(int pos = 0; pos < dishList.size(); pos++) {
            if (dishList.get(pos).getDishCount() != 0) {
                dishOrder.add(dishList.get(pos));
            }
        }
        buildRecyclerView();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new OrderAdapter(dishOrder);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onMinusButtonClick(int position) {
                int local_price = dishOrder.get(position).getDishPrice() * dishOrder.get(position).getDishCount();
                TextView count = mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.currentItemCount);
                TextView tv = mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.dishPrice1);
                if (!count.getText().toString().equals("0")){
                    dishOrder.get(position).setDishCount(dishOrder.get(position).getDishCount() - 1);
                    count.setText(Integer.toString(dishOrder.get(position).getDishCount()));

                    local_price -= dishOrder.get(position).getDishPrice();
                    tv.setText("$" + Integer.toString(local_price));

                }
            }

            @Override
            public void onPlusButtonClick(int position) {
                int local_price = dishOrder.get(position).getDishPrice() * dishOrder.get(position).getDishCount();
                TextView count = mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.currentItemCount);
                TextView tv = mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.dishPrice1);
                dishOrder.get(position).setDishCount(dishOrder.get(position).getDishCount() + 1);
                count.setText(Integer.toString(dishOrder.get(position).getDishCount()));
                local_price += dishOrder.get(position).getDishPrice();
                tv.setText("$" + Integer.toString(local_price));

            }
        });
    }
}