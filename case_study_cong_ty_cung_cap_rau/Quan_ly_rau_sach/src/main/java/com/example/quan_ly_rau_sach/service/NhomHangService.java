package com.example.quan_ly_rau_sach.service;

import com.example.quan_ly_rau_sach.entity.NhomHang;
import com.example.quan_ly_rau_sach.repository.INhomHangRepository;
import com.example.quan_ly_rau_sach.repository.NhomHangRepository;

import java.util.List;

public class NhomHangService implements INhomHangService{
    private INhomHangRepository nhomHangRepository = new NhomHangRepository();
    @Override
    public List<NhomHang> findAll() {
        return nhomHangRepository.findAll();
    }
}
