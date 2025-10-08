package com.example.quan_ly_rau_sach.repository;

import com.example.quan_ly_rau_sach.entity.HangHoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhomHangRepository implements INhomHangRepository{
    @Override
    public List<HangHoa> findAll() {
        List<HangHoa> nhomList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from nhom_hang");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                HangHoa category = new HangHoa(id, name);
                nhomList.add(category);
            }
        } catch (Exception e) {
            System.out.println("Lỗi" + e.getMessage());
        }
        return nhomList;
    }
}