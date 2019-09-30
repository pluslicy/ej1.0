package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.Product;
import com.briup.apps.ej.bean.extend.ProductExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductExtendMapper {
    List<Product> query(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("product") Product product);

    List<ProductExtend> queryProductCascadeCategory(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("product") Product product);

    ProductExtend selectByPrimaryKey(long id);

    long count(@Param("product") Product product);
}
