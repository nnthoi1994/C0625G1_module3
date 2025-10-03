package com.example.quan_ly_san_pham.sevice;

import com.example.quan_ly_san_pham.dto.ProductDto;
import com.example.quan_ly_san_pham.entity.Product;
import com.example.quan_ly_san_pham.repository.ProductRepository;
import com.example.quan_ly_san_pham.repository.IProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private IProductRepository productRepository = new ProductRepository();

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public boolean delete(int id) {
        return productRepository.delete(id);
    }


    @Override
    public boolean update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public List<ProductDto> search(String name, String categoryId) {
        return productRepository.search(name, categoryId);
    }

    @Override
    public Product findByIdProduct(int id) {
        return productRepository.findByIdProduct(id);
    }
}
