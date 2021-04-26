package com.example.CityBus;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CityBus.database.BusModel;
import com.example.CityBus.database.DatabaseAccess;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity2 extends Activity {
    public static String to;
    RecyclerView recyclerView;
    ArrayList<String> busnumb;
    ArrayList<String> froms;
    ArrayList<String> tos;
    ArrayList<String> routes;
    BusDataAdapter2 adapter;
    ArrayList<String> at;
    FirebaseDatabase database;
    String data;
    DatabaseReference senddata1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        database = FirebaseDatabase.getInstance();
        recyclerView = findViewById(R.id.rv_buses2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        senddata1 = database.getReference().child("recent");
        busnumb = new ArrayList<>();
        froms = new ArrayList<>();
        tos = new ArrayList<>();
        at = new ArrayList<>();
//
//        busnumb.add("Hello");
//        froms.add("Hello");
//        tos.add("Hello");
//        at.add("Hello");
        senddata1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    busnumb.add(snapshot.child("busnumber").getValue().toString());
                    data = (String) snapshot.child("busnumber").getValue();
                    froms.add(snapshot.child("startroute").getValue().toString());
                    tos.add(snapshot.child("endroute").getValue().toString());
                    at.add(snapshot.child("arrivaltime").getValue().toString());
                }
                adapter = new BusDataAdapter2(ListActivity2.this,busnumb,froms,tos,at);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}