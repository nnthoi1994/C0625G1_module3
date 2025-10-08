package com.example.quan_ly_rau_sach.repository;

import com.example.quan_ly_rau_sach.dto.SanPhamDto;
import com.example.quan_ly_rau_sach.entity.HangHoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HangHoaRepository implements IHangHoaRepository{
    private final static String SELECT_ALL = "SELECT h.ma_hang, h.don_vi_tinh, h.ten_hang_hoa, h.don_gia,n.ten_loai_hang AS tenNhomHang " + "FROM hang_hoa h " + "JOIN nhom_hang n ON h.ma_nhom_hang = n.ma_hang;";
    private final static String ADD_NEW = "insert into hang_hoa(ten_hang_hoa, don_vi_tinh, don_gia, ma_loai_hang) values (?,?,?,?)";
    private final static String DELETE = "DELETE FROM hang_hoa WHERE ma_hang_hoa = ?";
    private static final String SELECT_BY_ID_PRODUCT =  "SELECT h.ma_hang_hoa, h.ten_hang_hoa, h.don_gia,n.ten_nhom_hang AS tenNhomHang FROM hang_hoa h JOIN nhon_hang n ON h.ma_nhom_hang = n.ma_hang WHERE 1=1";
    private static final String UPDATE_PRODUCT = "UPDATE hang_hoa SET ten_hang = ?, don_gia = ?, ma_nhom_hang = ? WHERE ma_hang = ?";


    @Override
    public List<SanPhamDto> findAll() {
        Connection connection = BaseRepository.getConnectDB();
        List<SanPhamDto> sanPhamList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int maHang = resultSet.getInt("ma_hang");
                String tenHangHoa = resultSet.getString("ten_hang_hoa");
                String donViTinh = resultSet.getString("don_vi_tinh");
                int donGia = resultSet.getInt("don_gia");
                String tenNhom = resultSet.getString("ten_loai_hang");
                SanPhamDto sanPhamDto = new SanPhamDto(maHang,tenHangHoa, donViTinh,donGia, tenNhom);
                sanPhamList.add(sanPhamDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sanPhamList;
    }

    @Override
    public boolean add(HangHoa hangHoa) {
        return false;
    }

    @Override
    public boolean delete(int maHangHoa) {
        return false;
    }

    @Override
    public List<SanPhamDto> search(String tenHangHoa, String maNhomHang) {
        return List.of();
    }

    @Override
    public boolean update(HangHoa hangHoa) {
        return false;
    }

    @Override
    public HangHoa findByIdProduct(int maHangHoa) {
        return null;
    }
}
