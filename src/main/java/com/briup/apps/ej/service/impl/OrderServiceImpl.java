package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Order;
import com.briup.apps.ej.bean.OrderExample;
import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.dao.OrderMapper;
import com.briup.apps.ej.dao.extend.OrderExtendMapper;
import com.briup.apps.ej.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderExtendMapper orderExtendMapper;


    @Override
    public List<OrderExtend> query(Long customerId, Long waiterId) {
        return orderExtendMapper.query(customerId,waiterId);
    }

    @Override
    public List<Order> findAll() {
        OrderExample example = new OrderExample();
        return orderMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(Order order) throws Exception {
        if(order.getId()!=null){
            orderMapper.updateByPrimaryKey(order);
        } else {
            // 保存订单的时候自动将当前时间设置为订单时间
            long time = new Date().getTime();
            order.setOrderTime(time);
            orderMapper.insert(order);
        }
    }


    @Override
    public void deleteById(long id) throws Exception {
        Order order = orderMapper.selectByPrimaryKey(id);
        if(order == null){
            throw new Exception("要删除的订单信息不存在");
        }
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            orderMapper.deleteByPrimaryKey(id);
        }
    }
}
