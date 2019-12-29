package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.BaseUser;
import com.briup.apps.ej.bean.BaseUserExample;
import com.briup.apps.ej.bean.Customer;
import com.briup.apps.ej.bean.CustomerExample;
import com.briup.apps.ej.dao.BaseUserMapper;
import com.briup.apps.ej.dao.CustomerMapper;
import com.briup.apps.ej.dao.extend.CustomerExtendMapper;
import com.briup.apps.ej.service.ICustomerService;
import com.briup.apps.ej.utils.PageVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CustomerExtendMapper customerExtendMapper;
    @Resource
    private BaseUserMapper baseUserMapper;

    @Override
    public List<BaseUser> findAll() {
        BaseUserExample example = new BaseUserExample();
        BaseUserExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo("customer");
        return baseUserMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(BaseUser baseUser) throws Exception {
        if(baseUser.getId()!=null){
            baseUserMapper.updateByPrimaryKey(baseUser);
        } else {
            baseUser.setRegisterTime(new Date().getTime());
            baseUser.setType("customer");
            baseUser.setEnabled(true);
            baseUserMapper.insert(baseUser);
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
        BaseUser baseUser = baseUserMapper.selectByPrimaryKey(id);
        if(baseUser == null){
            throw new Exception("要删除的用户信息不存在");
        }
        baseUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            this.deleteById(id);
        }
    }
}
