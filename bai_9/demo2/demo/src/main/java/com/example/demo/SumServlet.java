package com.example.demo;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SumServlet",value = "/sum")
public class SumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // khi gửi lên bằng method get => chạy
        System.out.println("--------Get run-------------");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("form-sum.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // khi gửi lên bằng method post => chay
        System.out.println("--------Post run-------------");
        // lấy dữ liệu từ client gửi lên
        double num1 = Double.parseDouble(req.getParameter("n1"));
        double num2 = Double.parseDouble(req.getParameter("n2"));
        double calculator = num1*num2*0.01;
        req.setAttribute("calculator",calculator);
        req.setAttribute("num1",num1);
        req.setAttribute("num2",num2);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("form-sum.jsp");
        requestDispatcher.forward(req,resp);


    }
}