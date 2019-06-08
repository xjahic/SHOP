package com.learn2code.Shop.db.service.api;

import com.learn2code.Shop.domain.BoughtProduct;

import java.util.List;

public interface BoughtProductService {

    void add(BoughtProduct boughtProduct);

    List<BoughtProduct> getAllByCustomerId(int customerId);
}
