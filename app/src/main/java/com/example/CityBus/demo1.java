package com.example.CityBus;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CityBus.database.BusModel;
import com.example.CityBus.database.DatabaseAccess;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class demo1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public  static String to;

    private static final String [] routestrings = new String[]
            {
                    "Mumbai","Chennai","Nizamabad","Hyderabad","Goa","Delhi","UttarPradesh"

            };
    static final float END_SCALE = 0.7f;
    // ConstraintLayout contentView;
    LinearLayout contentView;

    AutoCompleteTextView e1,e2;
    RecyclerView recyclerView;
    ArrayList<String> busnumb;
    ArrayList<String> froms;
    ArrayList<String> tos;
    ArrayList<String> routes;
    BusDataAdapter adapter;
    ArrayList<String> SLL;
    ArrayList<String> DLL;
    ArrayList<String> PLL;
    ArrayList<String> at;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView menuIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

//        e1 = (AutoCompleteTextView) findViewById(R.id.source);
//        e2 = (AutoCompleteTextView) findViewById(R.id.destination);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);



        naviagtionDrawer();

//        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_activated_1,routestrings);

//        e1.setAdapter(adap);
//        e2.setAdapter(adap);
//        recyclerView = findViewById(R.id.rv_buses);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        busnumb = new ArrayList<>();
//        froms = new ArrayList<>();
//        tos = new ArrayList<>();
//        routes= new ArrayList<>();
//        SLL = new ArrayList<>();
//        DLL = new ArrayList<>();
//        PLL = new ArrayList<>();
//        at = new ArrayList<>();



    }

    //Navigation Drawer Functions
    private void naviagtionDrawer() {

        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_history:
                startActivity(new Intent(getApplicationContext(), CardClicked.class));
                break;
        }

        return true;
    }


//    public void getroutes(View view) {
//        DatabaseAccess databaseAccess =DatabaseAccess.getInstance(getApplicationContext());
//        databaseAccess.open();
//
//        String from = e1.getText().toString().trim();
//        to = e2.getText().toString().trim();
//
//
//        Cursor busdata = databaseAccess.getBusData(from,to);
//        if(busdata.getCount()==0){
//            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
//        }
//        else{
//            while(busdata.moveToNext()){
//                busnumb.add(busdata.getString(0));
//                froms.add(busdata.getString(1));
//                tos.add(busdata.getString(3));
//
//
//                System.out.println("details bus :"+busdata.getString(2));
//                System.out.println("details bus4 :"+busdata.getString(4));
//                System.out.println("details bus6 :"+busdata.getString(6));
//
//                SLL.add(busdata.getString(2));
//                DLL.add(busdata.getString(4));
//
//                PLL.add(busdata.getString(6));
//                at.add(busdata.getString(7));
//
//            }
//        }
//        adapter = new BusDataAdapter(demo1.this,busnumb,froms,tos,SLL,DLL,PLL,at);
//        recyclerView.setAdapter(adapter);
//        databaseAccess.close();
//    }
}
