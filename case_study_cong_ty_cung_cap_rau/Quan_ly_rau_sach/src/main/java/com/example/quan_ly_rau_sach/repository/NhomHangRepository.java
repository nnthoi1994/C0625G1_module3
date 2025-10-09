package com.example.quan_ly_rau_sach.repository;

import com.example.quan_ly_rau_sach.entity.HangHoa;
import com.example.quan_ly_rau_sach.entity.NhomHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhomHangRepository implements INhomHangRepository{
    @Override
    public List<NhomHang> findAll() {
        List<NhomHang> nhomList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from nhom_hang");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ma_nhom_hang");
                String name = resultSet.getString("ten_nhom_hang");
                NhomHang nhomHang = new NhomHang(id, name);
                nhomList.add(nhomHang);
            }
        } catch (Exception e) {
            System.out.println("Lỗi" + e.getMessage());
        }
        return nhomList;
    }
}