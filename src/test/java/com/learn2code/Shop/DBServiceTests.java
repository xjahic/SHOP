package com.learn2code.Shop;

import com.learn2code.Shop.db.service.api.CustomerService;
import com.learn2code.Shop.db.service.api.MerchantService;
import com.learn2code.Shop.domain.Customer;
import com.learn2code.Shop.domain.Merchant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBServiceTests {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

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
        Merchant merchant = new Merchant("name", "email", "address");
        Integer id = merchantService.add(merchant);
        assert id != null;
        merchant.setId(id);

        Merchant fromDB = merchantService.get(id);
        Assert.assertEquals(merchant, fromDB);

        List<Merchant> merchants = merchantService.getMerchants();
        Assert.assertEquals(1, merchants.size());
        Assert.assertEquals(merchant, merchants.get(0));
    }
}
