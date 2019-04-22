package com.learn2code.Shop;

import com.learn2code.Shop.domain.Customer;
import com.learn2code.Shop.domain.Merchant;
import com.learn2code.Shop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBInsertTests {

    private final String insertCustomer = "INSERT INTO customer(name, surname, email, address, age, phone_number) values (?, ?, ?, ?, ?, ?)";
    private final String insertMerchant = "INSERT INTO merchant(name, email, address) values (?, ?, ?)";
    private final String insertProduct = "INSERT INTO product(merchant_id, name, description, price, created_at, available) values (?, ?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void createProduct() {
        Product product = new Product();
        product.setMerchantId(1);
        product.setName("Klavesnica");
        product.setDescription("Velmi dobra klavesnica");
        product.setPrice(15.5);
        product.setCreatedAt(Timestamp.from(Instant.now()));
        product.setAvailable(10);

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertProduct);
                ps.setInt(1, product.getMerchantId());
                ps.setString(2, product.getName());
                ps.setString(3, product.getDescription());
                ps.setDouble(4, product.getPrice());
                ps.setTimestamp(5, product.getCreatedAt());
                ps.setInt(6, product.getAvailable());
                return ps;
            }
        });

    }

    @Test
    public void createCustomer() {
        Customer customer = new Customer();
        customer.setName("Ferko");
        customer.setSurname("Mrkvicka");
        customer.setEmail("xxx");
        customer.setAddress("xxx");
        customer.setAge(18);
        customer.setPhoneNumber("xxx");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertCustomer);
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getSurname());
                ps.setString(3, customer.getEmail());
                ps.setString(4, customer.getAddress());
                ps.setInt(5, customer.getAge());
                ps.setString(6, customer.getPhoneNumber());
                return ps;
            }
        });
    }

    @Test
    public void createMerchant() {
        Merchant merchant = new Merchant();
        merchant.setName("merchant");
        merchant.setEmail("email@email.learn2code.sk");
        merchant.setAddress("address3");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertMerchant);
                ps.setString(1, merchant.getName());
                ps.setString(2, merchant.getEmail());
                ps.setString(3, merchant.getAddress());
                return ps;
            }
        });
    }
}
