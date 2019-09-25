package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductExtendMapper {
    List<Product> query(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("product") Product product);

    long count(@Param("product") Product product);
}
