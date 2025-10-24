package com.example.bai_thi.repository;

import com.example.bai_thi.dto.PhongTroDto;
import com.example.bai_thi.entity.PhongTro;

import java.util.List;

public interface IPhongTroRepository {
    List<PhongTroDto> findAll();
    boolean add(PhongTro phongTro);
    boolean delete(int maPhongTro);
    List<PhongTroDto> search(String tenPhongTro, String maThanhToan);
    PhongTro findByIdProduct(int maPhongTro);
}
