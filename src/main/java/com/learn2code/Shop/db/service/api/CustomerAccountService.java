package com.learn2code.Shop.db.service.api;

import com.learn2code.Shop.domain.CustomerAccount;
import org.springframework.lang.Nullable;

public interface CustomerAccountService {

    void addCustomerAccount(CustomerAccount customerAccount);

    @Nullable
    Double getMoney(int customerId);

    void setMoney(int customerId, double money);
}
