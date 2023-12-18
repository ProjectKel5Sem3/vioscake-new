package com.example.vioscake.HomePart;

public class HomeItem {
    String NamaKue, HargaKue;
    int LogoKue;

    public HomeItem(String namaKue, String hargaKue, int logoKue) {
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