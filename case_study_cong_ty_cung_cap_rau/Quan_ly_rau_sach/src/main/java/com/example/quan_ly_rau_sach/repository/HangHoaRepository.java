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
    private final static String SELECT_ALL = "SELECT h.ma_hang, h.don_vi_tinh, h.ten_hang_hoa, h.don_gia,n.ten_nhom_hang AS tenNhomHang " + "FROM hang_hoa h " + "JOIN nhom_hang n ON h.ma_nhom_hang = n.ma_nhom_hang;";
    private final static String ADD_NEW = "insert into hang_hoa(ten_hang_hoa, don_vi_tinh, don_gia, ma_nhom_hang) values (?,?,?,?)";
    private final static String DELETE = "DELETE FROM hang_hoa WHERE ma_hang = ?";
    private static final String SELECT_BY_ID_PRODUCT =  "SELECT h.ma_hang, h.ten_hang_hoa,h.don_vi_tinh, h.don_gia,n.ten_nhom_hang AS tenNhomHang FROM hang_hoa h JOIN nhom_hang n ON h.ma_nhom_hang = n.ma_nhom_hang WHERE 1=1";
    private static final String UPDATE_PRODUCT = "UPDATE hang_hoa SET ten_hang_hoa = ?, don_vi_tinh = ?, don_gia = ?, ma_nhom_hang = ? WHERE ma_hang = ?";


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
                String tenNhom = resultSet.getString("tenNhomHang");
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
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(ADD_NEW);
            preparedStatement.setString(1, hangHoa.getTenHangHoa());
            preparedStatement.setString(2, hangHoa.getDonViTinh());
            preparedStatement.setInt(3, hangHoa.getDonGia());
            preparedStatement.setInt(4, hangHoa.getMaNhomHang());
            int row = preparedStatement.executeUpdate();
            return row==1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm mới: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int maHangHoa) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, maHangHoa);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SanPhamDto> search(String tenHangHoa, String maNhomHang) {
        List<SanPhamDto> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SELECT_BY_ID_PRODUCT);
        if (tenHangHoa != null && !tenHangHoa.isEmpty()) {
            sql.append(" AND h.ten_hang_hoa LIKE ?");
        }
        if (maNhomHang != null && !maNhomHang.isEmpty()) {
            sql.append(" AND n.ma_nhom_hang = ?");
        }
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int index = 1;
            if (tenHangHoa != null && !tenHangHoa.isEmpty()) {
                ps.setString(index++, "%" + tenHangHoa + "%");
            }
            if (maNhomHang != null && !maNhomHang.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(maNhomHang));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPhamDto(
                        rs.getInt("ma_hang"),
                        rs.getString("ten_hang_hoa"),
                        rs.getString("don_vi_tinh"),
                        rs.getInt("don_gia"),
                        rs.getString("tenNhomHang")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(HangHoa hangHoa) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {

            ps.setString(1, hangHoa.getTenHangHoa ());
            ps.setString(2, hangHoa.getDonViTinh());
            ps.setInt(3, hangHoa.getDonGia());
            ps.setInt(4, hangHoa.getMaNhomHang());
            ps.setInt(5, hangHoa.getMaHangHoa());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public HangHoa findByIdProduct(int maHangHoa) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement("SELECT p.ma_hang, p.ten_hang_hoa,p.don_vi_tinh, p.don_gia, p.ma_nhom_hang, c.ten_nhom_hang AS tenNhomHang " +
                     "FROM hang_hoa p JOIN nhom_hang c ON p.ma_nhom_hang = c.ma_nhom_hang WHERE p.ma_hang = ?")) {

            ps.setInt(1, maHangHoa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new HangHoa(
                        rs.getInt("ma_hang"),
                        rs.getString("ten_hang_hoa"),
                        rs.getString("don_vi_tinh"),
                        rs.getInt("don_gia"),
                        rs.getInt("ma_nhom_hang")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
