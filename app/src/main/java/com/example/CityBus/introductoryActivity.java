package com.example.CityBus;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class introductoryActivity extends AppCompatActivity {
    ImageView background,logo1;
    TextView app_name;
    LottieAnimationView lottieAnimationView;
    FloatingActionButton fab;
    /*LottieAnimationView lottieAnimationView1;
    LottieAnimationView lottieAnimationView2;*/

    private static final int NUM_PAGES =2;
    private ViewPager viewPager;
    private ScreenSlidePageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //We are initializing db firstTime


        setContentView(R.layout.activity_introductory);
        app_name = findViewById(R.id.app_name);
        background = findViewById(R.id.img1);
        logo1 = findViewById(R.id.cloud1);
        lottieAnimationView = findViewById(R.id.lotte);
       /* lottieAnimationView1 = findViewById(R.id.lotte1);
        lottieAnimationView2 = findViewById(R.id.lotte2);*/


        app_name.animate().translationY(-2100).setDuration(1000).setStartDelay(4000);
        background.animate().translationY(-2700).setDuration(1900).setStartDelay(4000);
        logo1.animate().translationY(2100).setDuration(2000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-1800).setDuration(1900).setStartDelay(4000);
        /*lottieAnimationView1.animate().translationY(1400).setDuration(100000).setStartDelay(4000);
        lottieAnimationView2.animate().translationY(1400).setDuration(100000).setStartDelay(2000);*/


        viewPager = findViewById(R.id.pager);
        pageAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        
    }




    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                    return tab1;
                case 1:

                    OnBoeardingFragment2 tab2 = new OnBoeardingFragment2();
                    return tab2;
            }
            return null;
        }



        @Override
        public int getCount()  {
            return NUM_PAGES;
        }
    }
}