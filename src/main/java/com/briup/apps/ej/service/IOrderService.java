package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Order;
import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.bean.vm.OrderAndOrderLineVM;
import com.briup.apps.ej.bean.vm.OrderVM;

import java.util.List;

public interface IOrderService {
    List<OrderVM> queryBasic(Long customerId, Long waiterId);

    List<OrderExtend> query(Long customerId,Long waiterId);

    List<Order> findAll();

    void save(OrderAndOrderLineVM order) throws Exception;

    void deleteById(long id) throws Exception;

    void batchDelete(long[] ids) throws Exception;
}
