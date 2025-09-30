package com.example.bai_tap_hien_thi_danh_sach_khach_hang.sevice;

import com.example.bai_tap_hien_thi_danh_sach_khach_hang.entity.Customer;
import com.example.bai_tap_hien_thi_danh_sach_khach_hang.repository.CustomerRepository;
import com.example.bai_tap_hien_thi_danh_sach_khach_hang.repository.ICustomerRepository;

import java.util.List;

public class CustomerService implements ICustomerService{
    private ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public boolean add(Customer customer) {
        return false;
    }
}
