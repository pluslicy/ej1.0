package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Order;
import com.briup.apps.ej.bean.OrderExample;
import com.briup.apps.ej.bean.OrderLine;
import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.bean.vm.OrderAndOrderLineVM;
import com.briup.apps.ej.bean.vm.OrderVM;
import com.briup.apps.ej.dao.OrderLineMapper;
import com.briup.apps.ej.dao.OrderMapper;
import com.briup.apps.ej.dao.extend.OrderExtendMapper;
import com.briup.apps.ej.service.IOrderService;
import com.briup.apps.ej.utils.PageVM;
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
    @Resource
    private OrderLineMapper orderLineMapper;

    @Override
    public List<OrderVM> queryBasic(Long customerId, Long waiterId) {
        return orderExtendMapper.queryBasic(customerId, waiterId);
    }

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
    public void save(OrderAndOrderLineVM order) throws Exception {
        // 先保存订单
        Order o = new Order();
        o.setOrderTime(new Date().getTime());
        o.setTotal(Double.valueOf(order.getOrderLines().get(0).getNumber()));
        o.setCustomerId(order.getCustomerId());
        o.setAddressId(order.getAddressId());
        orderMapper.insert(o);
        // 再保存订单项
        List<OrderLine> list = order.getOrderLines();
        for(OrderLine ol : list){
            // 维护订单项与订单之间的关系
            ol.setOrderId(o.getId());
            orderLineMapper.insert(ol);
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

    @Override
    public PageVM<Order> queryPage(int page, int pageSize, Order order) {
        List<Order> list = orderExtendMapper.queryPage(page,pageSize,order);
        long count = orderExtendMapper.count(order);
        return new PageVM<>(page,pageSize,count,list);
    }
}
