package com.example.quan_ly_san_pham.controller;

import com.example.quan_ly_san_pham.dto.ProductDto;
import com.example.quan_ly_san_pham.entity.Category;
import com.example.quan_ly_san_pham.entity.Product;
import com.example.quan_ly_san_pham.sevice.CategoryService;
import com.example.quan_ly_san_pham.sevice.ICategoryService;
import com.example.quan_ly_san_pham.sevice.ProductService;
import com.example.quan_ly_san_pham.sevice.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/products")
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();
    private ICategoryService categoryService = new CategoryService();

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
                deleteProduct(req, resp);
                break;
            default:
                List<ProductDto> productList = productService.findAll();
                req.setAttribute("productList", productList);
                req.getRequestDispatcher("views/product/list.jsp").forward(req, resp);
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
                 listProduct(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        Product product = new Product(name,price, categoryId);
        productService.add(product);
        resp.sendRedirect("/products");
    }

    private void formAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("views/product/add.jsp").forward(req, resp);
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ProductDto> productList = productService.findAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("views/product/list.jsp").forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        resp.sendRedirect("/products");
    }


}
