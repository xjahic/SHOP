package com.learn2code.Shop.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class BoughtProduct {
    private int productId;
    private int customerId;
    private int quantity;
    private Timestamp boughtAt;

    public BoughtProduct() {}

    public BoughtProduct(int productId, int customerId, int quantity) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.boughtAt = Timestamp.from(Instant.now());
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getBoughtAt() {
        return boughtAt;
    }

    public void setBoughtAt(Timestamp boughtAt) {
        this.boughtAt = boughtAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoughtProduct that = (BoughtProduct) o;
        return productId == that.productId &&
                customerId == that.customerId &&
                quantity == that.quantity &&
                Objects.equals(boughtAt, that.boughtAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerId, quantity, boughtAt);
    }
}
