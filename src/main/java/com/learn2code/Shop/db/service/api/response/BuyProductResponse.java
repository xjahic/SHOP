package com.learn2code.Shop.db.service.api.response;

import org.springframework.lang.Nullable;

import java.util.Objects;

public class BuyProductResponse {
    private boolean success;
    @Nullable
    private String errorMessage;

    public BuyProductResponse(boolean success) {
        this.success = success;
    }

    public BuyProductResponse(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyProductResponse response = (BuyProductResponse) o;
        return success == response.success &&
                Objects.equals(errorMessage, response.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, errorMessage);
    }
}
