package com.example.quan_ly_san_pham.repository;

import com.example.quan_ly_san_pham.dto.ProductDto;
import com.example.quan_ly_san_pham.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<ProductDto> findAll();
    boolean add(Product product);
    boolean delete(int id);
    List<Product> searchByName(String name, String categoryID);

}
