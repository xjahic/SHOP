package com.learn2code.Shop;

import com.learn2code.Shop.db.service.api.CustomerService;
import com.learn2code.Shop.db.service.api.MerchantService;
import com.learn2code.Shop.db.service.api.ProductService;
import com.learn2code.Shop.db.service.api.request.UpdateProductRequest;
import com.learn2code.Shop.domain.Customer;
import com.learn2code.Shop.domain.Merchant;
import com.learn2code.Shop.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBServiceTests {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ProductService productService;

    private static Merchant merchant;

    @BeforeEach
    public void createMerchant() {
        if (merchant == null) {
            merchant = new Merchant("name", "email", "address");
            Integer id = merchantService.add(merchant);
            assert id != null;
            merchant.setId(id);
        }
    }

    @Test
    public void customer() {
        Customer customer = new Customer("Ferko", "Mrkvicka", "emailtest", "test", 20, "xxx");
        Integer id = customerService.add(customer);
        assert id != null;
        customer.setId(id);

        Customer fromDb = customerService.get(id);
        Assertions.assertEquals(customer, fromDb);

        List<Customer> customers = customerService.getCustomers();
        Assertions.assertEquals(1, customers.size());
        Assertions.assertEquals(customer, customers.get(0));
    }

    @Test
    public void merchant() {
        // already created merchant in createMerchant function

        Merchant fromDB = merchantService.get(merchant.getId());
        Assertions.assertEquals(merchant, fromDB);

        List<Merchant> merchants = merchantService.getMerchants();
        Assertions.assertEquals(1, merchants.size());
        Assertions.assertEquals(merchant, merchants.get(0));
    }

    @Test
    public void product() {
        Product product = new Product(merchant.getId(), "name", "description", 5, 1);
        Integer id = productService.add(product);
        assert id != null;
        product.setId(id);

        Product fromDB = productService.get(id);
        Assertions.assertEquals(product, fromDB);

        List<Product> products = productService.getProducts();
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(product, products.get(0));

        product.setAvailable(10);
        UpdateProductRequest productRequest = new UpdateProductRequest(product.getName(), product.getDescription(), product.getPrice(), product.getAvailable());

        productService.update(id, productRequest);
        Product fromDBAfterUpdate = productService.get(id);
        Assertions.assertEquals(product, fromDBAfterUpdate);
        Assertions.assertNotEquals(fromDB, fromDBAfterUpdate);

        productService.delete(id);
        Assertions.assertEquals(0, productService.getProducts().size());
    }
}
