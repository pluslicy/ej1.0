package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Category;
import com.briup.apps.ej.utils.PageVM;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    void saveOrUpdate(Category category) throws Exception;

    void deleteById(long id) throws Exception;

    void batchDelete(long[] ids) throws Exception;

    PageVM<Category> query(int page, int pageSize, Category category);
}
