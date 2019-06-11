package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void saveOrUpdate(Customer customer) throws Exception;

    void deleteById(long id) throws Exception;

    void batchDelete(long[] ids) throws Exception;
}
