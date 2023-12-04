package com.example.vioscake;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Menambahkan onClickListener ke elemen dengan ID editProfile
        TextView editProfileButton = view.findViewById(R.id.Pengaturan);
        editProfileButton.setOnClickListener(this);

        return view;
    }

    // Metode yang akan dipanggil ketika elemen dengan ID editProfile diklik


    // Metode untuk memunculkan halaman edit_profile.xml
    private void loadEditProfilePage() {
        // Lakukan tindakan untuk memunculkan halaman edit_profile.xml
        // Misalnya, menggunakan FragmentTransaction untuk mengganti fragment
        EditProfile editProfileFragment = new EditProfile();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, editProfileFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Pengaturan) {
            // Munculkan halaman edit_profile.xml atau lakukan tindakan yang sesuai
            loadEditProfilePage();
        }
    }
}
