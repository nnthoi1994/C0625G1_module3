package com.example.bai_thi.controller;

import com.example.bai_thi.dto.PhongTroDto;
import com.example.bai_thi.entity.PhongTro;
import com.example.bai_thi.entity.ThanhToan;
import com.example.bai_thi.service.IPhongTroService;
import com.example.bai_thi.service.IThanhToanService;
import com.example.bai_thi.service.PhongTroService;
import com.example.bai_thi.service.ThanhToanService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PhongTroController", value = "/phongTro")
public class PhongTroController extends HttpServlet {
    private IPhongTroService phongTroService = new PhongTroService();
    private IThanhToanService thanhToanService = new ThanhToanService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action) {
            case "add":
                // trả về form thêm mới
                formAdd(req, resp);
                break;
            case "delete":
                deletePhongTro(req, resp);
                break;

            default:
                listPhongTro(req, resp);
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
                add(req, resp);
                break;
            case "delete":
                // xoá
                break;

            default:
                listPhongTro(req, resp);
        }
    }






    private void listPhongTro(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searchName = req.getParameter("tenPhongTro");
        String searchThanhToan = req.getParameter("maThanhToan");

        List<PhongTroDto> phongTroList;
        if ((searchName != null && !searchName.isEmpty()) ||
                (searchThanhToan != null && !searchThanhToan.isEmpty())) {
            phongTroList = phongTroService.search(searchName, searchThanhToan);
        } else {
            phongTroList = phongTroService.findAll();
        }
        List<ThanhToan> thanhToan = thanhToanService.findAll();
        req.setAttribute("phongTroList", phongTroList);
        req.setAttribute("thanhToan", thanhToan);
        req.setAttribute("selectedThanhToan", searchThanhToan);
        req.setAttribute("searchName", searchName);
        req.getRequestDispatcher("views/phong_tro/list.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String tenNguoiThueTro = req.getParameter("ten_nguoi_thue_tro");
        String soDienThoai = req.getParameter("so_dien_thoai");
        String ngayBatDau = req.getParameter("ngay_bat_dau");
        int maThanhToan = Integer.parseInt(req.getParameter("ma_thanh_toan"));
        String ghiChu = req.getParameter("ghi_chu");
        PhongTro phongTro = new PhongTro(tenNguoiThueTro, soDienThoai, ngayBatDau, maThanhToan, ghiChu);
        phongTroService.add(phongTro);
        resp.sendRedirect("/phongTro");
    }

    private void formAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ThanhToan> thanhToan = thanhToanService.findAll();
        req.setAttribute("thanhToan", thanhToan);
        req.getRequestDispatcher("views/phong_tro/add.jsp").forward(req, resp);
    }



    private void deletePhongTro(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int maPhongTro = Integer.parseInt(req.getParameter("maPhongTro"));
        phongTroService.delete(maPhongTro);
        resp.sendRedirect("/phongTro");
    }




}
