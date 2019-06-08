package com.learn2code.Shop.db.service.api;

import com.learn2code.Shop.db.service.api.request.BuyProductRequest;
import com.learn2code.Shop.db.service.api.response.BuyProductResponse;

public interface ShoppingService {
    BuyProductResponse buyProduct(BuyProductRequest request);
}
