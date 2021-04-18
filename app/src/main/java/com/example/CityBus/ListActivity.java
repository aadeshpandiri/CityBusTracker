package com.example.CityBus;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CityBus.database.BusModel;
import com.example.CityBus.database.DatabaseAccess;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends Activity {

    EditText e1,e2;
    RecyclerView recyclerView;
    ArrayList<String> busnumb;
    ArrayList<String> froms;
    ArrayList<String> tos;
    ArrayList<String> routes;
    BusDataAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        e1 = (EditText)findViewById(R.id.source);
        e2 = (EditText)findViewById(R.id.destination);
        recyclerView = findViewById(R.id.rv_buses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        busnumb = new ArrayList<>();
        froms = new ArrayList<>();
        tos = new ArrayList<>();
        routes= new ArrayList<>();

    }

    public void getroutes(View view) {
        DatabaseAccess databaseAccess =DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String from = e1.getText().toString().trim();
        String to = e2.getText().toString().trim();
        Cursor busdata = databaseAccess.getBusData(from,to);
        if(busdata.getCount()==0){
            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
        }
        else{
            while(busdata.moveToNext()){
                busnumb.add(busdata.getString(0));
                froms.add(busdata.getString(1));
                tos.add(busdata.getString(2));
                routes.add(busdata.getString(3));
            }
        }
        adapter = new BusDataAdapter(ListActivity.this,busnumb,froms,tos,routes);
        recyclerView.setAdapter(adapter);
        databaseAccess.close();
    }
}
