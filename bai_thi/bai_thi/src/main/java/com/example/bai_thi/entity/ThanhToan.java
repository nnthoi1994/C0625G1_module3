package com.example.bai_thi.entity;

public class ThanhToan {
    private int maThanhToan;
    private String tenThanhToan;

    public ThanhToan(int maThanhToan, String tenThanhToan) {
        this.maThanhToan = maThanhToan;
        this.tenThanhToan = tenThanhToan;
    }

    public int getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public String getTenThanhToan() {
        return tenThanhToan;
    }

    public void setTenThanhToan(String tenThanhToan) {
        this.tenThanhToan = tenThanhToan;
    }
}
