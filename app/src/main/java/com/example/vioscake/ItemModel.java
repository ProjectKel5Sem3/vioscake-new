package com.example.vioscake;

import androidx.fragment.app.Fragment;

public class ItemModel extends Fragment {

    String NamaKue, HargaKue;
    int LogoKue;

    public ItemModel(String namaKue, String hargaKue, int logoKue) {
        NamaKue = namaKue;
        HargaKue = hargaKue;
        LogoKue = logoKue;
    }

    public String getNamaKue() {
        return NamaKue;
    }

    public String getHargaKue() {
        return HargaKue;
    }

    public int getLogoKue() {
        return LogoKue;
    }
}
