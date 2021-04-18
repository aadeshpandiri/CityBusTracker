package com.example.CityBus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends AppCompatActivity {
    CircleMenu circleMenu;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleMenu = findViewById(R.id.menu);
        constraintLayout = findViewById(R.id.constraint_Layout);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.mipmap.menu_ic,R.mipmap.delete_ic)
                .addSubMenu(Color.parseColor("#88bef5"),R.mipmap.home_ic)
                .addSubMenu(Color.parseColor("#83e85a"),R.mipmap.logout_ic2)
                .addSubMenu(Color.parseColor("#FF4832"),R.mipmap.home_ic)
                .addSubMenu(Color.parseColor("#ba53de"),R.mipmap.home_ic)
                .addSubMenu(Color.parseColor("#ff8a5c"),R.mipmap.home_ic)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index){
                            case 0:
                                Toast.makeText(MainActivity.this, "Home",Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#FF4832"));
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this, "Logout",Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#96f7d2"));
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "Notification",Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#fac4a2"));
                                break;
                            case 3:
                                Toast.makeText(MainActivity.this, "GPS",Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#d3cde6"));
                                break;
                            case 4:
                                Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#fff591"));
                                break;
                        }
                    }
                });

    }
}