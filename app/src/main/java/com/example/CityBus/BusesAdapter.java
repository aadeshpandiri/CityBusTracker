package com.example.CityBus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.CityBus.database.BusModel;

import java.util.ArrayList;

public class BusesAdapter extends RecyclerView.Adapter<BusesAdapter.ViewHolder>{
    private ArrayList<BusModel> listdata;

    // RecyclerView recyclerView;
    public BusesAdapter(ArrayList<BusModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_bus, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BusModel myListData = listdata.get(position);
        //holder.mTVArrivalTV.setText(myListData.routeMap.get(""));
        holder.mTVDestination.setText(myListData.routeEnd);
        holder.mTVCurrentLocation.setText(myListData.routeMap.get("01:00"));
        holder.mTVBusname.setText(myListData.busName);
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTVArrivalTV, mTVDestination,mTVCurrentLocation, mTVBusname;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mTVArrivalTV = (TextView) itemView.findViewById(R.id.tv_arrival);
            this.mTVDestination = (TextView) itemView.findViewById(R.id.tv_destination);
            this.mTVCurrentLocation = (TextView) itemView.findViewById(R.id.tv_currentLocation);
            this.mTVBusname = (TextView) itemView.findViewById(R.id.tv_busname);

        }
    }
}
