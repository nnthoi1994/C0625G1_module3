package com.example.bai_thi.repository;

import com.example.bai_thi.entity.ThanhToan;

import java.util.List;

public interface IThanhToanRepository {
    List<ThanhToan> findAll();
}
