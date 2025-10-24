package com.example.bai_thi.entity;

public class PhongTro {
    private int maPhongTro;
    private String tenNguoiThueTro;
    private String soDienThoai;
    private String ngayBatDau;
    private int maThanhToan;
    private String ghiChu;

    public PhongTro(int maPhongTro, String tenNguoiThueTro, String soDienThoai, String ngayBatDau, int maThanhToan, String ghiChu) {
        this.maPhongTro = maPhongTro;
        this.tenNguoiThueTro = tenNguoiThueTro;
        this.soDienThoai = soDienThoai;
        this.ngayBatDau = ngayBatDau;
        this.maThanhToan = maThanhToan;
        this.ghiChu = ghiChu;
    }

    public PhongTro(String tenNguoiThueTro, String soDienThoai, String ngayBatDau, int maThanhToan, String ghiChu) {
        this.tenNguoiThueTro = tenNguoiThueTro;
        this.soDienThoai = soDienThoai;
        this.ngayBatDau = ngayBatDau;
        this.maThanhToan = maThanhToan;
        this.ghiChu = ghiChu;
    }

    public PhongTro() {
    }

    public int getMaPhongTro() {
        return maPhongTro;
    }

    public void setMaPhongTro(int maPhongTro) {
        this.maPhongTro = maPhongTro;
    }

    public String getTenNguoiThueTro() {
        return tenNguoiThueTro;
    }

    public void setTenNguoiThueTro(String tenNguoiThueTro) {
        this.tenNguoiThueTro = tenNguoiThueTro;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public int getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}


