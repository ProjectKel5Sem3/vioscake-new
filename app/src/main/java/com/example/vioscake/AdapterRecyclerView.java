package com.example.vioscake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview .widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    ArrayList<ItemModel> dataItem;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView NamaKue;
        TextView HargaKue;
        ImageView LogoKue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            NamaKue = itemView.findViewById(R.id.judultext);
            HargaKue = itemView.findViewById(R.id.hargatext);
            LogoKue = itemView.findViewById(R.id.poster);
        }
    }

    AdapterRecyclerView(ArrayList<ItemModel> data){
        this.dataItem = data;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.ViewHolder holder, int position) {

        TextView judultext = holder.NamaKue;
        TextView hargatext = holder.HargaKue;
        ImageView logokue = holder.LogoKue;

        judultext.setText(dataItem.get(position).getNamaKue());
        hargatext.setText(dataItem.get(position).getHargaKue());
        logokue.setImageResource(dataItem.get(position).getLogoKue());
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }


}
