package com.example.bai_tap_hien_thi_danh_sach_khach_hang.repository;

import com.example.bai_tap_hien_thi_danh_sach_khach_hang.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository{

    private static List<Customer> customerList = new ArrayList<>();
    static {
        customerList.add(new Customer(1,"chánh 1", "02-02-1993", "DaNang", "photo1"));
        customerList.add(new Customer(1,"chánh 2", "02-02-1994", "DaNang 1", "photo2"));
        customerList.add(new Customer(1,"chánh 3", "02-02-1995", "DaNang 2 ", "photo3"));
        customerList.add(new Customer(1,"chánh 4", "02-02-1996", "DaNang 3", "photo4"));
    }
    @Override
    public List<Customer> findAll() {
        return customerList;
    }

    @Override
    public boolean add(Customer customer) {
        return false;
    }
}
