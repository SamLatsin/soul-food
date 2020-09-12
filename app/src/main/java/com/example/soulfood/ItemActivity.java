package com.example.soulfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        String dishName = "";
        String dishDesc = "";
        int dishPrice = 0;
        int dishPhotoId = 0;

        dishName = getIntent().getExtras().getString("dishName");
        dishDesc = getIntent().getExtras().getString("dishDesc");
        dishPrice = getIntent().getExtras().getInt("dishPrice");
        dishPhotoId = getIntent().getExtras().getInt("dishPhotoId");
        TextView tv1 = (TextView)findViewById(R.id.textView7);
        TextView tv2 = (TextView)findViewById(R.id.textView8);
        TextView tv3 = (TextView)findViewById(R.id.textView9);
        ImageView img1 = (ImageView)findViewById(R.id.imageView4);
        tv1.setText(dishName);
        tv2.setText(dishDesc);
        tv3.setText("$" + Integer.toString(dishPrice));
        img1.setImageResource(dishPhotoId);
    }
}