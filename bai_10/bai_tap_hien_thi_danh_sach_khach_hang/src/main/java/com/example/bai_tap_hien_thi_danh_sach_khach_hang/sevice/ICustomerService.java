package com.example.bai_tap_hien_thi_danh_sach_khach_hang.sevice;

import com.example.bai_tap_hien_thi_danh_sach_khach_hang.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    boolean add(Customer customer);
}
