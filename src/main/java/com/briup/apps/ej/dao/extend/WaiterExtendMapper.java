package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.Waiter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaiterExtendMapper {
    List<Waiter> query(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("waiter") Waiter waiter);

    long count(@Param("waiter") Waiter waiter);
}
