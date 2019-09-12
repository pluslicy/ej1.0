package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Category;
import com.briup.apps.ej.bean.CategoryExample;
import com.briup.apps.ej.dao.CategoryMapper;
import com.briup.apps.ej.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ej
 * @description: 栏目服务类
 * @author: charles
 * @create: 2019-09-06 15:12
 **/
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectByExample(new CategoryExample());
    }

    @Override
    public void saveOrUpdate(Category category) throws Exception {
        if(category.getId()!=null){
            categoryMapper.updateByPrimaryKey(category);
        } else {
//            address.setStatus("正常");
            categoryMapper.insert(category);
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        Category address = categoryMapper.selectByPrimaryKey(id);
        if(address == null){
            throw new Exception("要删除的分类信息不存在");
        }
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            categoryMapper.deleteByPrimaryKey(id);
        }
    }

}
