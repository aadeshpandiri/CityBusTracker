package com.example.CityBus;

import android.view.LayoutInflater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BusDataAdapter2 extends RecyclerView.Adapter<BusDataAdapter2.ViewHolder> {


    Context context;
    ArrayList<String> busnumb;
    ArrayList<String> froms;
    ArrayList<String> tos;
    ArrayList<String> at;
    int number;

    FirebaseDatabase database;

    public BusDataAdapter2(Context context, ArrayList<String> busnumb, ArrayList<String> froms, ArrayList<String> tos,ArrayList<String> at) {
        this.context = context;
        this.busnumb = busnumb;
        this.froms = froms;
        this.tos = tos;
        this.at = at;
        database = FirebaseDatabase.getInstance();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_bus,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.busname.setText(busnumb.get(position));
        holder.startroute.setText(froms.get(position));
        holder.endroute.setText(tos.get(position));
        holder.atime.setText(at.get(position));
    }

    @Override
    public int getItemCount() {
        return busnumb.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView busname;
        TextView startroute;
        TextView endroute;
        TextView atime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            busname = itemView.findViewById(R.id.tv_busname);
            startroute = itemView.findViewById(R.id.tv_currentLocation);
            endroute = itemView.findViewById(R.id.tv_destination);
            atime = itemView.findViewById(R.id.tv_arrival);
        }
    }
}

