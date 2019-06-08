package com.learn2code.Shop.db.service.api.request;

import java.util.Objects;

public class BuyProductRequest {
    private int productId;
    private int customerId;
    private int quantity;

    public BuyProductRequest(int productId, int customerId, int quantity) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyProductRequest that = (BuyProductRequest) o;
        return productId == that.productId &&
                customerId == that.customerId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerId, quantity);
    }
}
