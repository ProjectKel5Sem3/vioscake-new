package com.example.vioscake;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vioscake.EntryPackage.Login;

public class Profile extends Fragment implements View.OnClickListener {
    TextView logoutProfile;
    String logoutUrl = "https://yourbackend.com/logoutEndpoint";
    public Profile(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Menambahkan onClickListener ke elemen dengan ID editProfile
        TextView editProfileButton = view.findViewById(R.id.Pengaturan);
        logoutProfile = view.findViewById(R.id.LogoutProfile);
        editProfileButton.setOnClickListener(this);

        logoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile();
            }
        });

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
    private void deleteProfile() {
        RequestQueue queue = Volley.newRequestQueue(requireActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, logoutUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Response berhasil diterima, lakukan proses logout
                        // Misalnya, kembali ke halaman login
                        Intent intent = new Intent(requireActivity(), Login.class);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle jika ada kesalahan saat melakukan permintaan
                Toast.makeText(requireContext(), "Gagal melakukan logout.", Toast.LENGTH_SHORT).show();
            }
        });

        // Tambahkan request ke Queue
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Pengaturan) {
            // Munculkan halaman edit_profile.xml atau lakukan tindakan yang sesuai
            loadEditProfilePage();
        }
    }
}
