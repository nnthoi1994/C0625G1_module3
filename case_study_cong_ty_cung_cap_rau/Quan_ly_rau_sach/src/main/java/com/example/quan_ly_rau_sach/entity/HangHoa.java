package com.example.quan_ly_rau_sach.entity;

public class HangHoa {
    private int maHangHoa;
    private String tenHangHoa;
    private String donViTinh;
    private int donGia;
    private int maNhomHang;

    public HangHoa(int maHangHoa, String tenHangHoa, String donViTinh, int donGia, int maNhomHang) {
        this.maHangHoa = maHangHoa;
        this.tenHangHoa = tenHangHoa;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.maNhomHang = maNhomHang;
    }

    public HangHoa() {
    }

    public HangHoa(String tenHangHoa, String donViTinh, int donGia, int maNhomHang) {
        this.tenHangHoa = tenHangHoa;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.maNhomHang = maNhomHang;
    }

    public int getMaHangHoa() {
        return maHangHoa;
    }

    public void setMaHangHoa(int maHangHoa) {
        this.maHangHoa = maHangHoa;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getMaNhomHang() {
        return maNhomHang;
    }

    public void setMaNhomHang(int maNhomHang) {
        this.maNhomHang = maNhomHang;
    }
}

