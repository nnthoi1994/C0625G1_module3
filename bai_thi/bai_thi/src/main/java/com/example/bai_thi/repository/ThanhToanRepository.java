package com.example.bai_thi.repository;

import com.example.bai_thi.entity.ThanhToan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThanhToanRepository implements IThanhToanRepository{
    @Override
    public List<ThanhToan> findAll() {
        List<ThanhToan> nhomList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from thanh_toan");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ma_thanh_toan");
                String name = resultSet.getString("ten_thanh_toan");
                ThanhToan thanhToan = new ThanhToan(id, name);
                nhomList.add(thanhToan);
            }
        } catch (Exception e) {
            System.out.println("Lỗi" + e.getMessage());
        }
        return nhomList;
    }
}