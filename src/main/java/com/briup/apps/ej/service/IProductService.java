package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Product;
import com.briup.apps.ej.utils.PageVM;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void saveOrUpdate(Product product) throws Exception;

    void deleteById(long id) throws Exception;

    List<Product> findByCategoryId(long id);

    void batchDelete(long[] ids) throws Exception;

    PageVM<Product> query(int page, int pageSize, Product product);
}
