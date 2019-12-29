package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.BaseUser;
import com.briup.apps.ej.bean.Customer;
import com.briup.apps.ej.utils.PageVM;


import java.util.List;

public interface ICustomerService {
    List<BaseUser> findAll();

    PageVM<Customer> query(int page, int pageSize, Customer customer);

    void saveOrUpdate(BaseUser baseUser) throws Exception;

    void deleteById(long id) throws Exception;

    void batchDelete(long[] ids) throws Exception;
}
