package com.learn2code.Shop;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn2code.Shop.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void customer() throws Exception {
        Customer customer = new Customer("Ferko", "Mrkvicka", "email", "address", 21, "xxx");

        // Add customer
        String id = mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        customer.setId(objectMapper.readValue(id, Integer.class));

        // Get customer
        String customerJson = mockMvc.perform(get("/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Customer returnedCustomer = objectMapper.readValue(customerJson, Customer.class);
        Assert.assertEquals(customer, returnedCustomer);

        // Get all customers
        String listJson = mockMvc.perform(get("/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Customer> customers = objectMapper.readValue(listJson, new TypeReference<List<Customer>>() {
        });
        assert customers.size() == 1;
        Assert.assertEquals(customer, customers.get(0));
    }


}
