package com.example.quan_ly_rau_sach.service;

import com.example.quan_ly_rau_sach.dto.SanPhamDto;
import com.example.quan_ly_rau_sach.entity.HangHoa;

import java.util.List;

public interface IHangHoaService {
    List<SanPhamDto> findAll();
    boolean add(HangHoa hangHoa);
    boolean delete(int maHangHoa);
    List<SanPhamDto> search(String tenHangHoa, String maNhomHang);
    boolean update(HangHoa hangHoa);
    HangHoa findByIdProduct(int maHangHoa);
}
