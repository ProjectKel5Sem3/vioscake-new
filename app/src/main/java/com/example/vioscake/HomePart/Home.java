package com.example.vioscake.HomePart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vioscake.R;

import java.util.ArrayList;

public class Home extends Fragment {

    RecyclerView recyclerView;a
    AdapterRVHome adapterRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<HomeItem> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycleproduk);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < KueItem.NamaKue.length; i++){
            data.add(new HomeItem(
                    KueItem.NamaKue[i],
                    KueItem.HargaKue[i],
                    KueItem.LogoKue[i]
            ));
        }


        adapterRecyclerView = new AdapterRVHome(data);
        recyclerView.setAdapter(adapterRecyclerView);


        return view;
    }

}