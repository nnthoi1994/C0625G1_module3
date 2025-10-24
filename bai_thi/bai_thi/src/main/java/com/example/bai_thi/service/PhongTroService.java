package com.example.bai_thi.service;

import com.example.bai_thi.dto.PhongTroDto;
import com.example.bai_thi.entity.PhongTro;
import com.example.bai_thi.repository.IPhongTroRepository;
import com.example.bai_thi.repository.PhongTroRepository;

import java.util.List;

public class PhongTroService implements IPhongTroService{
    private final IPhongTroRepository phongTroRepository= new PhongTroRepository();

    @Override
    public List<PhongTroDto> findAll() {
        return phongTroRepository.findAll();
    }

    @Override
    public boolean add(PhongTro phongTro) {
        return phongTroRepository.add(phongTro);
    }

    @Override
    public boolean delete(int maPhongTro) {
        return phongTroRepository.delete(maPhongTro);
    }

    @Override
    public List<PhongTroDto> search(String tenPhongTro, String maThanhToan) {
        return List.of();
    }
}
