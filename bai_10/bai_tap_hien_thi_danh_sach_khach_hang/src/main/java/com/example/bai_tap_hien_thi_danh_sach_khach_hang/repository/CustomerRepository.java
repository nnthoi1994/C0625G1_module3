package com.example.bai_tap_hien_thi_danh_sach_khach_hang.repository;

import com.example.bai_tap_hien_thi_danh_sach_khach_hang.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerRepository implements ICustomerRepository{
    private final static String SELECT_ALL = "select * from khach_hang";
    private final static String ADD_NEW = "insert into khach_hang(ho_ten,ngay_sinh,dia_chi,email) values (?,?,?,?)";



//    private static List<Customer> customerList = new ArrayList<>();
//    static {
//        customerList.add(new Customer(1,"chánh 1", "02-02-1993", "DaNang", "email1"));
//        customerList.add(new Cus tomer(1,"chánh 2", "02-02-1994", "DaNang 1", "email2"));
//        customerList.add(new Customer(1,"chánh 3", "02-02-1995", "DaNang 2 ", "email3"));
//        customerList.add(new Customer(1,"chánh 4", "02-02-1996", "DaNang 3", "email4"));
//    }
    @Override
    public List<Customer> findAll() {
        Connection connection = BaseRepository.getConnectDB();
        List<Customer> customerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt("ma_khach_hang");
                String name = resultSet.getString("ho_ten");
                String dateOfBirth = resultSet.getString("ngay_sinh");
                String address = resultSet.getString("dia_chi");
                String email = resultSet.getString("email");
                Customer customer = new Customer(id,name, dateOfBirth, address, email);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public boolean add(Customer customer) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(ADD_NEW);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getDateOfBirth());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getEmail());
            int row = preparedStatement.executeUpdate();
            return row==1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm mới: " + e.getMessage());
            return false;
        }
    }
}
