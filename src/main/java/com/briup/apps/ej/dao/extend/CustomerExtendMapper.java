package com.briup.apps.ej.dao.extend;


import com.briup.apps.ej.bean.Customer;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @program: ej
 * @description: customer拓展接口
 * @author: charles
 * @create: 2019-09-24 17:06
 **/

public interface CustomerExtendMapper {
    List<Customer> query(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("customer") Customer customer);

    long count(@Param("customer") Customer customer);

}
