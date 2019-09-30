package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Product;
import com.briup.apps.ej.bean.extend.ProductExtend;
import com.briup.apps.ej.utils.PageVM;

import java.util.List;

public interface IProductService {
    /**
     * @Description: 查询所有产品信息
     * @Param: []
     * @return: java.util.List<com.briup.apps.ej.bean.Product>
     * @Author: charles
     * @Date: 2019-09-30
     */
    List<Product> findAll();

    /**
     * @Description: 通过id查找产品信息
     * @Param: [id]
     * @return: com.briup.apps.ej.bean.extend.ProductExtend
     * @Author: charles
     * @Date: 2019-09-30
     */
    ProductExtend findById(long id);

    void saveOrUpdate(Product product) throws Exception;

    void deleteById(long id) throws Exception;

    List<Product> findByCategoryId(long id);

    void batchDelete(long[] ids) throws Exception;

    PageVM<Product> query(int page, int pageSize, Product product);

    PageVM<ProductExtend> queryProductCascadeCategory(int page, int pageSize, Product product);
}
