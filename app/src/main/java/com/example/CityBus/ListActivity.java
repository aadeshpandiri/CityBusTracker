package com.example.CityBus;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    public  static String to;

    private static final String [] routestrings = new String[]
            {
                    "Nizamabad","Hyderabad","Pune","Delhi","Mumbai","Chennai"
            };

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        e1 = (AutoCompleteTextView) findViewById(R.id.source);
        e2 = (AutoCompleteTextView) findViewById(R.id.destination);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1,routestrings);

        e1.setAdapter(adap);
        e2.setAdapter(adap);
        recyclerView = findViewById(R.id.rv_buses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        busnumb = new ArrayList<>();
        froms = new ArrayList<>();
        tos = new ArrayList<>();
        routes= new ArrayList<>();
        SLL = new ArrayList<>();
        DLL = new ArrayList<>();
        PLL = new ArrayList<>();
        at = new ArrayList<>();



    }

    public void getroutes(View view) {
        DatabaseAccess databaseAccess =DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String from = e1.getText().toString().trim();
         to = e2.getText().toString().trim();


        Cursor busdata = databaseAccess.getBusData(from,to);
        if(busdata.getCount()==0){
            Toast.makeText(this, "Not Found", Toast.LENGTH_LONG).show();
        }
        else{
            while(busdata.moveToNext()){
                busnumb.add(busdata.getString(0));
                froms.add(busdata.getString(1));
                tos.add(busdata.getString(3));


                System.out.println("details bus :"+busdata.getString(2));
                System.out.println("details bus4 :"+busdata.getString(4));
                System.out.println("details bus6 :"+busdata.getString(6));

                SLL.add(busdata.getString(2));
                DLL.add(busdata.getString(4));

                PLL.add(busdata.getString(6));
                at.add(busdata.getString(7));

            }
        }
        adapter = new BusDataAdapter(ListActivity.this,busnumb,froms,tos,SLL,DLL,PLL,at);
        recyclerView.setAdapter(adapter);
        databaseAccess.close();
    }
}
