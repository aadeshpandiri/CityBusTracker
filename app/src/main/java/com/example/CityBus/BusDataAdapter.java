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

import java.util.ArrayList;
import java.util.List;

public class BusDataAdapter extends RecyclerView.Adapter<BusDataAdapter.ViewHolder> {

    //    BusData[] busData;
    Context context;
    ArrayList<String> busnumb;
    ArrayList<String> froms;
    ArrayList<String> tos;
    ArrayList<String> routes;


    public BusDataAdapter(Context context, ArrayList<String> busnumb, ArrayList<String> froms, ArrayList<String> tos, ArrayList<String> routes) {
        this.context = context;
        this.busnumb = busnumb;
        this.froms = froms;
        this.tos = tos;
        this.routes = routes;
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

        String routeList = routes.get(position);
        String pickuppoint = froms.get(position);
        holder.busname.setText(busnumb.get(position));
        holder.startroute.setText(froms.get(position));
        holder.endroute.setText(tos.get(position));
        StringBuilder time= new StringBuilder(routeList.substring(1,6));
        if(time.indexOf("=")!=-1){
            time.deleteCharAt(time.length()-1);
        }
        holder.timing.setText(time.toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(time);
                Intent intent = new Intent(context, CardClicked.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("data",routeList);
                intent.putExtra("pickup",pickuppoint);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busnumb.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView busname;
        TextView startroute;
        TextView endroute;
        TextView timing;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            busname = itemView.findViewById(R.id.tv_busname);
            startroute = itemView.findViewById(R.id.tv_currentLocation);
            endroute = itemView.findViewById(R.id.tv_destination);
            timing = itemView.findViewById(R.id.tv_arrival);

        }
    }
}

