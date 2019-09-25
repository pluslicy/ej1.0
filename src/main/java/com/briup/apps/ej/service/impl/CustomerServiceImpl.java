package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Customer;
import com.briup.apps.ej.bean.CustomerExample;
import com.briup.apps.ej.dao.CustomerMapper;
import com.briup.apps.ej.dao.extend.CustomerExtendMapper;
import com.briup.apps.ej.service.ICustomerService;
import com.briup.apps.ej.utils.PageVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CustomerExtendMapper customerExtendMapper;
    @Override
    public List<Customer> findAll() {
        CustomerExample example = new CustomerExample();
        return customerMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(Customer customer) throws Exception {
        if(customer.getId()!=null){
            customerMapper.updateByPrimaryKey(customer);
        } else {
            customer.setStatus("正常");
            customerMapper.insert(customer);
        }
    }
    @Override
    public PageVM<Customer> query(int page, int pageSize, Customer customer) {
        List<Customer> list = customerExtendMapper.query(page,pageSize,customer);
        long count = customerExtendMapper.count(customer);
        return new PageVM<>(page,pageSize,count,list);
    }

    @Override
    public void deleteById(long id) throws Exception {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(customer == null){
            throw new Exception("要删除的用户信息不存在");
        }
        customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            customerMapper.deleteByPrimaryKey(id);
        }
    }
}
