package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Waiter;
import com.briup.apps.ej.utils.PageVM;

import java.util.List;

public interface IWaiterService {
    List<Waiter> findAll();

    void saveOrUpdate(Waiter waiter) throws Exception;

    void deleteById(long id) throws Exception;

    void batchDelete(long[] ids) throws Exception;

    PageVM<Waiter> query(int page, int pageSize, Waiter waiter);
}
