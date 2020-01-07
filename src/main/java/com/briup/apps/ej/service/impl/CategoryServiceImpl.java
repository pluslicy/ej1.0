package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Category;
import com.briup.apps.ej.bean.CategoryExample;
import com.briup.apps.ej.dao.CategoryMapper;
import com.briup.apps.ej.dao.extend.CategoryExtendMapper;
import com.briup.apps.ej.service.ICategoryService;
import com.briup.apps.ej.utils.PageVM;
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
    @Resource
    private CategoryExtendMapper categoryExtendMapper;
    @Override
    public List<Category> findAll() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("`num` ASC");
        return categoryMapper.selectByExample(example);
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

    @Override
    public PageVM<Category> query(int page, int pageSize, Category category) {
        List<Category> list = categoryExtendMapper.query(page,pageSize,category);
        long count = categoryExtendMapper.count(category);
        return new PageVM<>(page,pageSize,count,list);
    }

}
