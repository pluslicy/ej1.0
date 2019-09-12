package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Waiter;
import com.briup.apps.ej.bean.WaiterExample;
import com.briup.apps.ej.dao.WaiterMapper;
import com.briup.apps.ej.service.IWaiterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public List<Waiter> findAll() {
        return waiterMapper.selectByExample(new WaiterExample());
    }

    @Override
    public void saveOrUpdate(Waiter waiter) throws Exception {
        if(waiter.getId()!=null){
            waiterMapper.updateByPrimaryKey(waiter);
        } else {
//            address.setStatus("正常");
            waiterMapper.insert(waiter);
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        Waiter product = waiterMapper.selectByPrimaryKey(id);
        if(product == null){
            throw new Exception("要删除的服务员信息不存在");
        }
        waiterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            waiterMapper.deleteByPrimaryKey(id);
        }
    }
}
