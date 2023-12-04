package com.example.vioscake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;

public class EditProfile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile, container, false);

        ImageView backButton = view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan logika yang ingin Anda jalankan saat tombol "Back" diklik
                requireActivity().onBackPressed();  // Ini akan kembali ke tumpukan sebelumnya atau menutup fragment jika tidak ada tumpukan.
            }
        });

        return view;
    }
}
