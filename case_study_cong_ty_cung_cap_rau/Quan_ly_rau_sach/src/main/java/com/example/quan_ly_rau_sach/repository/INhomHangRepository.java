package com.example.quan_ly_rau_sach.repository;

import com.example.quan_ly_rau_sach.entity.HangHoa;

import java.util.List;

public interface INhomHangRepository {
    List<HangHoa> findAll();
}
