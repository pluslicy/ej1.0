package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.*;
import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.bean.vm.OrderAndOrderLineVM;
import com.briup.apps.ej.bean.vm.OrderVM;
import com.briup.apps.ej.dao.BaseUserMapper;
import com.briup.apps.ej.dao.OrderLineMapper;
import com.briup.apps.ej.dao.OrderMapper;
import com.briup.apps.ej.dao.WaiterMapper;
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
    @Resource
    private WaiterMapper waiterMapper;
    @Resource
    private BaseUserMapper baseUserMapper;

    @Override
    public OrderExtend findOrderDetailsById(long id) {
        return orderExtendMapper.selectById(id);
    }

    @Override
    public List<OrderVM> queryBasic(Long customerId, Long waiterId) {
        return orderExtendMapper.queryBasic(customerId, waiterId);
    }

    @Override
    public List<OrderExtend> query(Long customerId, Long waiterId,String status) {
        return orderExtendMapper.query(customerId,waiterId,status);
    }

    @Override
    public List<Order> findAll() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("`order_time` DESC");
        return orderMapper.selectByExample(example);
    }

    @Override
    public void save(OrderAndOrderLineVM order) throws Exception {
        // 先保存订单
        Order o = new Order();
        // 目前默认为待派单
        o.setStatus(OrderExtend.STATUS_DAIPAIDAN);
        o.setOrderTime(new Date().getTime());
        o.setCustomerId(order.getCustomerId());
        o.setAddressId(order.getAddressId());
        // 再保存订单项
        List<OrderLine> list = order.getOrderLines();
        double total = 0;
        for(OrderLine ol : list) {
            total += ol.getPrice() * ol.getNumber();
        }
        o.setTotal(Double.valueOf(total));

        orderMapper.insert(o);

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

    @Override
    public void sendOrder(long waiterId, long orderId) throws Exception {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        BaseUser user = baseUserMapper.selectByPrimaryKey(waiterId);
        if(order == null){
            throw new Exception("该订单不存在");
        }
        if(user == null) {
            throw new Exception("该员工不存在");
        }
        // 派单
        order.setStatus(OrderExtend.STATUS_DAIJIEDAN);
        order.setWaiterId(waiterId);
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public void cancelSendOrder(long orderId) throws Exception {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            throw new Exception("该订单不存在");
        }
        order.setStatus(OrderExtend.STATUS_DAIPAIDAN);
        // 取消订单
        order.setWaiterId(null);
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public void takeOrder( long orderId) throws Exception {
        //更新订单状态为未服务状态
        changeOrderStatus(orderId,OrderExtend.STATUS_DAIFWU);
    }

    @Override
    public void rejectOrder(long orderId) throws Exception {
        // 更新订单状态为待派单
        changeOrderStatus(orderId,OrderExtend.STATUS_DAIPAIDAN);
    }

    @Override
    public void serviceCompleted(long orderId) throws Exception {
        // 更新订单状态为待确认
        changeOrderStatus(orderId,OrderExtend.STATUS_DAIQUEREN);
    }

    @Override
    public void confirmOrder(long orderId) throws Exception {
        // 更新订单状态为已完成
        changeOrderStatus(orderId,OrderExtend.STATUS_COMPLETE);
    }

    private void changeOrderStatus( long orderId, String status) throws Exception {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            throw new Exception("该订单不存在");
        }
        order.setStatus(status);
        if(status.equals(OrderExtend.STATUS_DAIPAIDAN)){
            order.setWaiterId(null);
        }
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public List<OrderLine> getOrderLinesByOrderId(long orderId) {
        OrderLineExample example = new OrderLineExample();
        example.setOrderByClause("`order_time` DESC");
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderLineMapper.selectByExample(example);
    }
}
