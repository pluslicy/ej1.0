package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.OrderLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderLineExtendMapper {
    List<OrderLine> selectByOrderId(long orderId);
}
