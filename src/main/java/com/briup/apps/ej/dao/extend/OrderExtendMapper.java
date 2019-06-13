package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.bean.vm.OrderVM;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderExtendMapper {
    List<OrderExtend> query(
            @Param("customerId") Long customerId,
            @Param("waiterId") Long waiterId
    );

    List<OrderVM> queryBasic(
            @Param("customerId") Long customerId,
            @Param("waiterId") Long waiterId
    );
}
