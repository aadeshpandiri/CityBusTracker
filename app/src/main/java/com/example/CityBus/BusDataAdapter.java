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
    ArrayList<String> SLL;
    ArrayList<String> PLL;
    ArrayList<String> DLL;



    public BusDataAdapter(Context context, ArrayList<String> busnumb, ArrayList<String> froms, ArrayList<String> tos,ArrayList<String> SLL,ArrayList<String> DLL,ArrayList<String> PLL) {
        this.context = context;
        this.busnumb = busnumb;
        this.froms = froms;
        this.tos = tos;
        this.SLL = SLL;
        this.PLL = PLL;
        this.DLL = DLL;

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


        String pickuppoint = froms.get(position);
        holder.busname.setText(busnumb.get(position));
        holder.startroute.setText(froms.get(position));
        holder.endroute.setText(tos.get(position));

        String sllpoints = SLL.get(position);
        String pllpoints = PLL.get(position);
        String dllpoints = DLL.get(position);

        String sdata[] = sllpoints.split(",");
        String slong = sdata[0].trim();
        String slat = sdata[1].trim();

        String ddata[] = dllpoints.split(",");
        String dlong = ddata[0].trim();
        String dlat = ddata[1].trim();

        String pdata[] = pllpoints.split(",");
        String plong = pdata[0].trim();
        String plat = pdata[1].trim();

//        if(time.indexOf("=")!=-1){
//            time.deleteCharAt(time.length()-1);
//        }
//        holder.timing.setText(time.toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println(time);
                Intent intent = new Intent(context, CardClicked.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("slong",slong);
                intent.putExtra("slat",slat);
                intent.putExtra("plong",plong);
                intent.putExtra("plat",plat);
                intent.putExtra("dlong",dlong);
                intent.putExtra("dlat",dlat);

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

