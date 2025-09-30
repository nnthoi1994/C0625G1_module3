package com.example.bai_tap_hien_thi_danh_sach_khach_hang.controller;

import com.example.bai_tap_hien_thi_danh_sach_khach_hang.entity.Customer;
import com.example.bai_tap_hien_thi_danh_sach_khach_hang.sevice.CustomerService;
import com.example.bai_tap_hien_thi_danh_sach_khach_hang.sevice.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customers")
public class CustomerController extends HttpServlet {
    private ICustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action) {
            case "add":
                // trả về form thêm mới
                req.getRequestDispatcher("views/customer/add.jsp").forward(req, resp);

                break;
            case "delete":
                // xoá
                break;
            default:
                List<Customer> customerList = customerService.findAll();
                req.setAttribute("customerList", customerList);
                req.getRequestDispatcher("views/customer/list.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action) {
            case "add":
                // trả về form thêm mới
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String dateOfBirth = req.getParameter("dateOfBirth");
                String address = req.getParameter("adress");
                String photo = req.getParameter("photo");
                Customer customer = new Customer(id,name,dateOfBirth, address, photo);
                customerService.add(customer);
                List<Customer> customerList2 = customerService.findAll();
                req.setAttribute("customerList", customerList2);
                req.getRequestDispatcher("views/customer/list.jsp").forward(req, resp);

                break;
            case "delete":
                // xoá
                break;
            default:
                List<Customer> customerList = customerService.findAll();
                req.setAttribute("customerList", customerList);
                req.getRequestDispatcher("views/customer/list.jsp").forward(req, resp);
        }
    }
}
