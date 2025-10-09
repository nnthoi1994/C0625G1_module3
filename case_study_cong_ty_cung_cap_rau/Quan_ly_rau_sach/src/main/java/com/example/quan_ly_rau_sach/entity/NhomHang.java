package com.example.quan_ly_rau_sach.entity;

public class NhomHang {
    private int maNhomHang;
    private String tenNhomHang;

    public NhomHang(int maNhomHang, String tenNhomHang) {
        this.maNhomHang = maNhomHang;
        this.tenNhomHang = tenNhomHang;
    }

    public NhomHang() {
    }

    public int getMaNhomHang() {
        return maNhomHang;
    }

    public void setMaNhomHang(int maNhomHang) {
        this.maNhomHang = maNhomHang;
    }

    public String getTenNhomHang() {
        return tenNhomHang;
    }

    public void setTenNhomHang(String tenNhomHang) {
        this.tenNhomHang = tenNhomHang;
    }
}
