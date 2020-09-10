package com.example.soulfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        final Intent intent = new Intent(IntroActivity.this, ShopActivity.class);
        intent.putExtra("restaurant", "Choose your restaurant");
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });


        LinearLayout menu_photos = (LinearLayout )findViewById(R.id.linearLayout1);
        menu_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(IntroActivity.this, R.style.MyAlertDialogStyle);
                final CharSequence[] items = new CharSequence[] {"Dinner in the Sky", "Parallax Restaurant", "El Diablo “The Devil”", "Signs", "Norma’s", "The Disaster Café", "Ninja Dining", "Aurum", "Forbes Island", "Rogo’s", "RAW Canvas"};

                adb.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv = (TextView)findViewById(R.id.textView1);
                        tv.setText(items[which]);
                        tv.setTextColor(Color.BLACK);

                        intent.putExtra("restaurant", items[which]);
                      //  startActivity(intent);
                    }
                });
                adb.setTitle("Choose your restaurant");
                adb.show();
            }
        });



    }
}