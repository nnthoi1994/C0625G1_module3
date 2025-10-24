package com.example.bai_thi.service;

import com.example.bai_thi.entity.ThanhToan;
import com.example.bai_thi.repository.IThanhToanRepository;
import com.example.bai_thi.repository.ThanhToanRepository;

import java.util.List;

public class ThanhToanService implements IThanhToanService{
    private final IThanhToanRepository thanhToanRepository= new ThanhToanRepository();
    @Override
    public List<ThanhToan> findAll() {
        return thanhToanRepository.findAll();
    }
}
