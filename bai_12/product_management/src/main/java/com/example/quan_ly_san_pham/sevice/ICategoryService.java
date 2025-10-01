package com.example.quan_ly_san_pham.sevice;

import com.example.quan_ly_san_pham.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
}
