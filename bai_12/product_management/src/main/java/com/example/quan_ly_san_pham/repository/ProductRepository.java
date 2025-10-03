package com.example.quan_ly_san_pham.repository;

import com.example.quan_ly_san_pham.dto.ProductDto;
import com.example.quan_ly_san_pham.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final static String SELECT_ALL = "SELECT p.id, p.name, p.price,c.name AS categoryName " + "FROM products p " + "JOIN categories c ON p.category_id = c.id;";
    private final static String ADD_NEW = "insert into products(name,price,category_id) values (?,?,?)";
    private final static String DELETE = "DELETE FROM Products WHERE id = ?";
    private static final String SELECT_BY_ID_PRODUCT =  "SELECT p.id, p.name, p.price, c.name AS categoryName FROM products p JOIN categories c ON p.category_id = c.id WHERE 1=1";
    private static final String UPDATE_PRODUCT = "UPDATE products SET name = ?, price = ?, category_id = ? WHERE id = ?";

    @Override
    public List<ProductDto> findAll() {
        Connection connection = BaseRepository.getConnectDB();
        List<ProductDto> productList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String categoryName = resultSet.getString("categoryName");
                ProductDto product = new ProductDto(id,name, price, categoryName);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public boolean add(Product product) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(ADD_NEW);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getCategoryId());
            int row = preparedStatement.executeUpdate();
            return row==1;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm mới: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }





    public List<ProductDto> search(String name, String categoryId) {
        List<ProductDto> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SELECT_BY_ID_PRODUCT);
        if (name != null && !name.isEmpty()) {
            sql.append(" AND p.name LIKE ?");
        }
        if (categoryId != null && !categoryId.isEmpty()) {
            sql.append(" AND c.id = ?");
        }
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int index = 1;
            if (name != null && !name.isEmpty()) {
                ps.setString(index++, "%" + name + "%");
            }
            if (categoryId != null && !categoryId.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(categoryId));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("categoryName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(Product product) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {

            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getCategoryId());
            ps.setInt(4, product.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product findByIdProduct(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement("SELECT p.id, p.name, p.price, p.category_id, c.name AS categoryName " +
                     "FROM products p JOIN categories c ON p.category_id = c.id WHERE p.id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("category_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
