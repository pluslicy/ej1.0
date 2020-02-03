package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.Order;
import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.bean.vm.OrderVM;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderExtendMapper {
    OrderExtend selectById(long id);

    List<OrderExtend> query(
            @Param("customerId") Long customerId,
            @Param("waiterId") Long waiterId,
            @Param("status") String status
    );

    List<OrderVM> queryBasic(
            @Param("customerId") Long customerId,
            @Param("waiterId") Long waiterId
    );
    List<Order> queryPage(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("order") Order order);

    long count(@Param("order") Order order);
}
