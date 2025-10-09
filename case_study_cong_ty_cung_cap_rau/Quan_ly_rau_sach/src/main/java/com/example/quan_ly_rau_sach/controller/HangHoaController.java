package com.example.quan_ly_rau_sach.controller;

import com.example.quan_ly_rau_sach.dto.SanPhamDto;
import com.example.quan_ly_rau_sach.entity.HangHoa;
import com.example.quan_ly_rau_sach.entity.NhomHang;
import com.example.quan_ly_rau_sach.service.HangHoaService;
import com.example.quan_ly_rau_sach.service.IHangHoaService;
import com.example.quan_ly_rau_sach.service.INhomHangService;
import com.example.quan_ly_rau_sach.service.NhomHangService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HangHoaController", value = "/hangHoa")
public class HangHoaController extends HttpServlet {
    private IHangHoaService hangHoaService = new HangHoaService();
    private INhomHangService nhomHangService = new NhomHangService();

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
                deleteHangHoa(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            default:
                listHangHoa(req, resp);
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
            case "edit":
                editHangHoa(req, resp);
                break;
            default:
                listHangHoa(req, resp);
        }
    }


    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        HangHoa hangHoa = hangHoaService.findByIdProduct(id);
        List<NhomHang> nhomHang = nhomHangService.findAll();

        req.setAttribute("hangHoa", hangHoa);
        req.setAttribute("nhomHang", nhomHang);
        req.getRequestDispatcher("views/hang_hoa/edit.jsp").forward(req, resp);
    }

    private void editHangHoa(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int maHang = Integer.parseInt(request.getParameter("ma_hang"));
            String tenHang = request.getParameter("ten_hang_hoa");
            String donViTinh = request.getParameter("don_vi_tinh");
            int donGia = Integer.parseInt(request.getParameter("don_gia"));
            int maLoaiHang = Integer.parseInt(request.getParameter("ma_nhom_hang"));

            HangHoa hangHoa = new HangHoa(maHang,tenHang,donViTinh,donGia,maLoaiHang);

            boolean updated = hangHoaService.update(hangHoa);
            if (updated) {
                System.out.println("Update thành công: " + maHang);
            } else {
                System.out.println("Update thất bại: " + maHang);
            }

            response.sendRedirect("/hangHoa");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("/hangHoa");
        }
    }

    private void listHangHoa(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searchName = req.getParameter("tenHang");
        String searchNhomHang = req.getParameter("maLoaiHang");

        List<SanPhamDto> sanPhamList;
        if ((searchName != null && !searchName.isEmpty()) ||
                (searchNhomHang != null && !searchNhomHang.isEmpty())) {
            sanPhamList = hangHoaService.search(searchName, searchNhomHang);
        } else {
            sanPhamList = hangHoaService.findAll();
        }
        List<NhomHang> nhomHang = nhomHangService.findAll();
        req.setAttribute("sanPhamList", sanPhamList);
        req.setAttribute("nhomHang", nhomHang);
        req.setAttribute("selectedNhomHang", searchNhomHang);
        req.setAttribute("searchName", searchName);
        req.getRequestDispatcher("views/hang_hoa/list.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String tenHang = req.getParameter("ten_hang_hoa");
        String donViTinh = req.getParameter("don_vi_tinh");
        int donGia = Integer.parseInt(req.getParameter("don_gia"));
        int maNhomHang = Integer.parseInt(req.getParameter("ma_nhom_hang"));
        HangHoa hangHoa = new HangHoa(tenHang, donViTinh, donGia, maNhomHang);
        hangHoaService.add(hangHoa);
        resp.sendRedirect("/hangHoa");
    }

    private void formAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<NhomHang> nhomHang = nhomHangService.findAll();
        req.setAttribute("nhomHang", nhomHang);
        req.getRequestDispatcher("views/hang_hoa/add.jsp").forward(req, resp);
    }



    private void deleteHangHoa(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int maHangHoa = Integer.parseInt(req.getParameter("maHangHoa"));
        hangHoaService.delete(maHangHoa);
        resp.sendRedirect("/hangHoa");
    }




}
