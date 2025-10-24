package com.example.bai_thi.repository;

import com.example.bai_thi.dto.PhongTroDto;
import com.example.bai_thi.entity.PhongTro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongTroRepository implements IPhongTroRepository{
    private final static String SELECT_ALL = "SELECT p.ma_phong_tro, p.ten_nguoi_thue_tro, p.so_dien_thoai, p.ngay_bat_dau, t.ten_thanh_toan AS tenThanhToan, p.ghi_chu " + "FROM phong_tro p " + "JOIN thanh_toan t ON p.ma_thanh_toan = t.ma_thanh_toan;";
    private final static String ADD_NEW = "insert into phong_tro(ten_nguoi_thue_tro, so_dien_thoai, ngay_bat_dau, ma_thanh_toan, ghi_chu) values (?,?,?,?,?)";
    private final static String DELETE = "DELETE FROM phong_tro WHERE ma_phong_tro = ?";
    private static final String SELECT_BY_ID_PRODUCT =  "SELECT p.ma_phong_tro, p.ten_phong_tro,p.so_dien_thoai, p.ngay_bat_dau,t.ten_thanh_toan AS tenThanhToan FROM phong_tro h JOIN thanh_toan t ON p.ma_thanh_toan = n.ma_thanh_toan WHERE 1=1";

    @Override
    public List<PhongTroDto> findAll() {
        Connection connection = BaseRepository.getConnectDB();
        List<PhongTroDto> phongTroList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int maPhongTro = resultSet.getInt("ma_phong_tro");
                String tenNguoiThueTro = resultSet.getString("ten_nguoi_thue_tro");
                String soDienThoai = resultSet.getString("so_dien_thoai");
                String ngayBatDau = resultSet.getString("ngay_bat_dau");
                String tenThanhToan = resultSet.getString("tenThanhToan");
                String ghiChu = resultSet.getString("ghi_chu");
                PhongTroDto phongTroDto = new PhongTroDto(maPhongTro, tenNguoiThueTro, soDienThoai, ngayBatDau, tenThanhToan, ghiChu);
                phongTroList.add(phongTroDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return phongTroList;
    }

    @Override
    public boolean add(PhongTro phongTro) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(ADD_NEW);
            preparedStatement.setString(1, phongTro.getTenNguoiThueTro());
            preparedStatement.setString(2, phongTro.getSoDienThoai());
            preparedStatement.setString(3, phongTro.getNgayBatDau());
            preparedStatement.setInt(4, phongTro.getMaThanhToan());
            preparedStatement.setString(5, phongTro.getGhiChu());
            int row = preparedStatement.executeUpdate();
            return row==1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm mới: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int maPhongTro) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, maPhongTro);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PhongTro findByIdProduct(int maPhongTro) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement("SELECT p.ma_phong_tro, p.ten_nguoi_thue_tro, p.so_dien_thoai, p.ngay_bat_dau, t.ten_thanh_toan AS tenThanhToan, p.ghi_chu " +
                     "FROM phong_tro p JOIN thanh_toan t ON p.ma_thanh_toan = t.ma_thanh_toan WHERE p.ma_phong_tro = ?")) {

            ps.setInt(1, maPhongTro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PhongTro(
                        rs.getInt("ma_phong_tro"),
                        rs.getString("ten_nguoi_thue_tro"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("ngay_bat_dau"),
                        rs.getInt("ma_thanh_toan"),
                        rs.getString("ghi_chu")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PhongTroDto> search(String tenPhongTro, String maThanhToan) {
        List<PhongTroDto> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SELECT_BY_ID_PRODUCT);
        if (tenPhongTro != null && !tenPhongTro.isEmpty()) {
            sql.append(" AND h.ten_phong_tro LIKE ?");
        }
        if (maThanhToan != null && !maThanhToan.isEmpty()) {
            sql.append(" AND n.ma_thanh_toan = ?");
        }
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int index = 1;
            if (tenPhongTro != null && !tenPhongTro.isEmpty()) {
                ps.setString(index++, "%" + tenPhongTro + "%");
            }
            if (maThanhToan != null && !maThanhToan.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(maThanhToan));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PhongTroDto(
                        rs.getInt("ma_phong_tro"),
                        rs.getString("ten_nguoi_thue_phong_tro"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("ngay_bat_dau"),

                        rs.getString("tenThanhToan"),
                        rs.getString("ghi_chu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;


    }
}
