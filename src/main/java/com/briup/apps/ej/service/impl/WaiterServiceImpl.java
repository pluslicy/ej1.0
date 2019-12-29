package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.BaseUser;
import com.briup.apps.ej.bean.BaseUserExample;
import com.briup.apps.ej.bean.Waiter;
import com.briup.apps.ej.bean.WaiterExample;
import com.briup.apps.ej.bean.extend.WaiterExtend;
import com.briup.apps.ej.dao.BaseUserMapper;
import com.briup.apps.ej.dao.WaiterMapper;
import com.briup.apps.ej.dao.extend.WaiterExtendMapper;
import com.briup.apps.ej.service.IWaiterService;
import com.briup.apps.ej.utils.PageVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: ej
 * @description: 服务员服务类
 * @author: charles
 * @create: 2019-09-06 15:12
 **/
@Service
public class WaiterServiceImpl implements IWaiterService {
    @Resource
    private WaiterMapper waiterMapper;
    @Resource
    private WaiterExtendMapper waiterExtendMapper;
    @Resource
    private BaseUserMapper baseUserMapper;

    @Override
    public List<BaseUser> findAll() {
        BaseUserExample example = new BaseUserExample();
        BaseUserExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo("waiter");
        return baseUserMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(BaseUser baseUser) throws Exception {
        if(baseUser.getId()!=null){
           baseUserMapper.updateByPrimaryKey(baseUser);
        } else {
            baseUser.setType("waiter");
            baseUser.setEnabled(true);
            baseUser.setRegisterTime(new Date().getTime());
            baseUserMapper.insert(baseUser);
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        BaseUser baseUser = baseUserMapper.selectByPrimaryKey(id);
        if(baseUser == null){
            throw new Exception("要删除的服务员信息不存在");
        }
        baseUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            baseUserMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageVM<Waiter> query(int page, int pageSize, Waiter waiter) {
        List<Waiter> list = waiterExtendMapper.query(page,pageSize,waiter);
        long count = waiterExtendMapper.count(waiter);
        return new PageVM<>(page,pageSize,count,list);
    }
}
