package com.learn2code.Shop.db.mapper;

import com.learn2code.Shop.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setMerchantId(rs.getInt("merchant_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setCreatedAt(rs.getTimestamp("created_at"));
        product.setAvailable(rs.getInt("available"));

        return product;
    }
}
