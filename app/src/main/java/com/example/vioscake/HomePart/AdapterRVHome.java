package com.example.vioscake.HomePart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vioscake.R;

import java.util.ArrayList;

public class AdapterRVHome extends RecyclerView.Adapter<AdapterRVHome.ViewHolder> {

    ArrayList<HomeItem> dataItem;

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

    AdapterRVHome(ArrayList<HomeItem> data){
        this.dataItem = data;
    }

    @NonNull
    @Override
    public AdapterRVHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRVHome.ViewHolder holder, int position) {

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
