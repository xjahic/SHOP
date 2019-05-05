package com.learn2code.Shop.db.repository;

import com.learn2code.Shop.db.mapper.ProductRowMapper;
import com.learn2code.Shop.db.service.api.request.UpdateProductRequest;
import com.learn2code.Shop.domain.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper = new ProductRowMapper();

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product get(int id) {
        final String sql = "select * from product where product.id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer add(Product product) {
        final String sql = "insert into product(merchant_id,name,description,price,created_at,available) values (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, product.getMerchantId());
                ps.setString(2, product.getName());
                ps.setString(3, product.getDescription());
                ps.setDouble(4, product.getPrice());
                if (product.getCreatedAt() == null) {
                    product.setCreatedAt(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(5, product.getCreatedAt());
                ps.setInt(6, product.getAvailable());
                return ps;
            }
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().intValue();
        } else {
            return null;
        }
    }

    public List<Product> getAll() {
        final String sql = "select * from product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public void update(int id, UpdateProductRequest request) {
        final String sql = "update product set name = ?, description = ?, price = ?, available = ? where id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getDescription(), request.getPrice(), request.getAvailable(), id);
    }

    public void delete(int id) {
        final String sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
