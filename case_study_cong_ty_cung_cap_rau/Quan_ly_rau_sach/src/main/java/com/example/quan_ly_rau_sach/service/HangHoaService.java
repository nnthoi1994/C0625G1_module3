package com.example.quan_ly_rau_sach.service;

import com.example.quan_ly_rau_sach.dto.SanPhamDto;
import com.example.quan_ly_rau_sach.entity.HangHoa;
import com.example.quan_ly_rau_sach.repository.HangHoaRepository;
import com.example.quan_ly_rau_sach.repository.IHangHoaRepository;
import org.eclipse.tags.shaded.org.apache.bcel.Repository;

import java.util.List;

public class HangHoaService implements IHangHoaService{
private IHangHoaRepository hangHoaRepository = new HangHoaRepository();
    @Override
    public List<SanPhamDto> findAll() {
        return hangHoaRepository.findAll();
    }

    @Override
    public boolean add(HangHoa hangHoa) {
        return hangHoaRepository.add(hangHoa);
    }

    @Override
    public boolean delete(int maHangHoa) {
        return hangHoaRepository.delete(maHangHoa);
    }

    @Override
    public List<SanPhamDto> search(String tenHangHoa, String maNhomHang) {
        return hangHoaRepository.search(tenHangHoa,maNhomHang);
    }

    @Override
    public boolean update(HangHoa hangHoa) {
        return hangHoaRepository.update(hangHoa);
    }

    @Override
    public HangHoa findByIdProduct(int maHangHoa) {
        return hangHoaRepository.findByIdProduct(maHangHoa);
    }
}
