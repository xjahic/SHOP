package com.learn2code.Shop;

import com.learn2code.Shop.db.service.api.CustomerService;
import com.learn2code.Shop.db.service.api.MerchantService;
import com.learn2code.Shop.db.service.api.ProductService;
import com.learn2code.Shop.db.service.api.request.UpdateProductRequest;
import com.learn2code.Shop.domain.Customer;
import com.learn2code.Shop.domain.Merchant;
import com.learn2code.Shop.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
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

    @Before
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
        Assert.assertEquals(customer, fromDb);

        List<Customer> customers = customerService.getCustomers();
        Assert.assertEquals(1, customers.size());
        Assert.assertEquals(customer, customers.get(0));
    }

    @Test
    public void merchant() {
        // already created merchant in createMerchant function

        Merchant fromDB = merchantService.get(merchant.getId());
        Assert.assertEquals(merchant, fromDB);

        List<Merchant> merchants = merchantService.getMerchants();
        Assert.assertEquals(1, merchants.size());
        Assert.assertEquals(merchant, merchants.get(0));
    }

    @Test
    public void product() {
        Product product = new Product(merchant.getId(), "name", "description", 5, 1);
        Integer id = productService.add(product);
        assert id != null;
        product.setId(id);

        Product fromDB = productService.get(id);
        Assert.assertEquals(product, fromDB);

        List<Product> products = productService.getProducts();
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(product, products.get(0));

        product.setAvailable(10);
        UpdateProductRequest productRequest = new UpdateProductRequest(product.getName(), product.getDescription(), product.getPrice(), product.getAvailable());

        productService.update(id, productRequest);
        Product fromDBAfterUpdate = productService.get(id);
        Assert.assertEquals(product, fromDBAfterUpdate);
        Assert.assertNotEquals(fromDB, fromDBAfterUpdate);

        productService.delete(id);
        Assert.assertEquals(0, productService.getProducts().size());
    }
}
