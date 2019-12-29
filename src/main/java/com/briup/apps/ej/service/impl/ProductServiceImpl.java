package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Product;
import com.briup.apps.ej.bean.ProductExample;
import com.briup.apps.ej.bean.extend.ProductExtend;
import com.briup.apps.ej.dao.ProductMapper;
import com.briup.apps.ej.dao.extend.ProductExtendMapper;
import com.briup.apps.ej.service.IProductService;
import com.briup.apps.ej.utils.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ej
 * @description: 产品是实现类
 * @author: charles
 * @create: 2019-06-10 17:50
 **/
@Service
public class ProductServiceImpl implements IProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductExtendMapper productExtendMapper;
    @Override
    public List<Product> findAll() {
        ProductExample example = new ProductExample();
        return productMapper.selectByExample(example);
    }



    @Override
    public ProductExtend findById(long id) {
        return productExtendMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> findByCategoryId(long id) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCategoryIdEqualTo(id);
        return productMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(Product product) throws Exception {
        if(product.getId()!=null){
            productMapper.updateByPrimaryKey(product);
        } else {
            product.setStatus("正常");
            productMapper.insert(product);
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        Product product = productMapper.selectByPrimaryKey(id);
        if(product == null){
            throw new Exception("要删除的产品信息不存在");
        }
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            productMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageVM<Product> query(int page, int pageSize, Product product) {
        List<Product> list = productExtendMapper.query(page,pageSize,product);
        long count = productExtendMapper.count(product);
        return new PageVM<>(page,pageSize,count,list);
    }

    @Override
    public PageVM<ProductExtend> queryProductCascadeCategory(int page, int pageSize, Product product) {
        List<ProductExtend> list = productExtendMapper.queryProductCascadeCategory(page,pageSize,product);
        long count = productExtendMapper.count(product);
        return new PageVM<>(page,pageSize,count,list);
    }
}
