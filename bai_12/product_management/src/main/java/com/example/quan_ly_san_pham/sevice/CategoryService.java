package com.example.quan_ly_san_pham.sevice;

import com.example.quan_ly_san_pham.entity.Category;
import com.example.quan_ly_san_pham.repository.CategoryRepository;
import com.example.quan_ly_san_pham.repository.ICategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService{
    private final ICategoryRepository categoryRepository = new CategoryRepository();
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
