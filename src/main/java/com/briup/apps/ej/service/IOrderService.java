package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Order;
import com.briup.apps.ej.bean.OrderLine;
import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.bean.vm.OrderAndOrderLineVM;
import com.briup.apps.ej.bean.vm.OrderVM;
import com.briup.apps.ej.utils.PageVM;

import java.util.List;

public interface IOrderService {
    /**
     * @Description:
     * @Param: [id]
     * @return:
     * @Author: charles
     * @Date: 2020-01-07
     */
    OrderExtend findOrderDetailsById(long id);
    /** 
     * @Description: 普通条件查询
     * @Param: [customerId, waiterId] 
     * @return: java.util.List<com.briup.apps.ej.bean.vm.OrderVM> 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    List<OrderVM> queryBasic(Long customerId, Long waiterId);
    
    /** 
     * @Description: 级联条件查询 
     * @Param: [customerId, waiterId] 
     * @return: java.util.List<com.briup.apps.ej.bean.extend.OrderExtend> 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    List<OrderExtend> query(Long customerId,Long waiterId,String status);
    
    /** 
     * @Description: 查询所有 
     * @Param: [] 
     * @return: java.util.List<com.briup.apps.ej.bean.Order> 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    List<Order> findAll();

    /** 
     * @Description: 保存订单 
     * @Param: [order] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    void save(OrderAndOrderLineVM order) throws Exception;
    
    /** 
     * @Description: 通过id删除 
     * @Param: [id] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    void deleteById(long id) throws Exception;

    /** 
     * @Description: 批量删除 
     * @Param: [ids] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    void batchDelete(long[] ids) throws Exception;

    /** 
     * @Description: 分页查询 
     * @Param: [page, pageSize, order] 
     * @return: com.briup.apps.ej.utils.PageVM<com.briup.apps.ej.bean.Order> 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    PageVM<Order> queryPage(int page, int pageSize, Order order);
    
    /** 
     * @Description: 派单 
     * @Param: [customerId, orderId] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-09-30 
     */
    void sendOrder(long waiterId,long orderId) throws Exception;
    
    /** 
     * @Description: 接单 
     * @Param: [ orderId]
     * @return: void 
     * @Author: charles 
     * @Date: 2019-10-02 
     */
    void takeOrder(long orderId) throws Exception;
    /**
     * @Description: 拒绝订单
     * @Param: [ orderId]
     * @return: void
     * @Author: charles
     * @Date: 2019-10-02
     */
    void rejectOrder(long orderId) throws Exception;
    /**
     * @Description: 服务结束 
     * @Param: [waiterId, orderId] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-10-02 
     */ 
    void serviceCompleted(long orderId) throws Exception;
    /**
     * @Description: 取消派单
     * @Param: [ orderId]
     * @return: void 
     * @Author: charles 
     * @Date: 2019-09-30 
     */ 
    void cancelSendOrder(long orderId) throws Exception;
    /**
     * @Description: 确认订单，服务费用到员工账户上
     * @Param: [waiterId, orderId]
     * @return: void
     * @Author: charles
     * @Date: 2019-10-27
     */
    void confirmOrder(long orderId) throws Exception;
    
    /** 
     * @Description: 通过订单编号查询订单项详情
     * @Param: [orderId] 
     * @return: java.util.List<com.briup.apps.ej.bean.OrderLine> 
     * @Author: charles 
     * @Date: 2019-11-02 
     */ 
    List<OrderLine> getOrderLinesByOrderId(long orderId) ;
}
