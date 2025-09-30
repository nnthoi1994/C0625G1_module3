package com.example.bai_tap_hien_thi_danh_sach_khach_hang.repository;

import com.example.bai_tap_hien_thi_danh_sach_khach_hang.entity.Customer;

import java.beans.Customizer;
import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    boolean add(Customer customer);

}
