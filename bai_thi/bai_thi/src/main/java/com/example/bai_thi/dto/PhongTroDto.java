package com.example.bai_thi.dto;

public class PhongTroDto {
    private int maPhongTro;
    private String tenNguoiThueTro;
    private String soDienThoai;
    private String ngayBatDau;
    private String tenThanhToan;
    private String ghiChu;

    public PhongTroDto(int maPhongTro, String tenNguoiThueTro, String soDienThoai, String ngayBatDau, String tenThanhToan, String ghiChu) {
        this.maPhongTro = maPhongTro;
        this.tenNguoiThueTro = tenNguoiThueTro;
        this.soDienThoai = soDienThoai;
        this.ngayBatDau = ngayBatDau;
        this.tenThanhToan = tenThanhToan;
        this.ghiChu = ghiChu;
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

    public String getTenThanhToan() {
        return tenThanhToan;
    }

    public void setTenThanhToan(String tenThanhToan) {
        this.tenThanhToan = tenThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
