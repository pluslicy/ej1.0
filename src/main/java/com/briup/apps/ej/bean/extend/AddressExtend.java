package com.briup.apps.ej.bean.extend;

import com.briup.apps.ej.bean.Address;
import com.briup.apps.ej.bean.Customer;

public class AddressExtend extends Address {
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
