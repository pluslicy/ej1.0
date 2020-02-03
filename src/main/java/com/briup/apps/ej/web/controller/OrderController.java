package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.Order;
import com.briup.apps.ej.bean.OrderLine;
import com.briup.apps.ej.bean.extend.OrderExtend;
import com.briup.apps.ej.bean.vm.OrderAndOrderLineVM;
import com.briup.apps.ej.bean.vm.OrderVM;
import com.briup.apps.ej.service.IOrderService;
import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import com.briup.apps.ej.utils.PageVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(description = "订单管理相关接口")
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("findById")
    @ApiOperation("根据id查询订单的详细信息")
    public Message findById(long id){
        OrderExtend orderVM = orderService.findOrderDetailsById(id);
        return MessageUtil.success("success",orderVM);
    }

    @GetMapping("queryBasic")
    @ApiOperation("查询订单信息，返回列表数据")
    public Message queryBasic(Long customerId,Long waiterId){
        List<OrderVM> list = orderService.queryBasic(customerId,waiterId);
        return MessageUtil.success("success",list);
    }

    @GetMapping("query")
    @ApiOperation("查询订单信息，并且订单级联关键的属性")
    public Message query(Long customerId,Long waiterId,String status){
        List<OrderExtend> list = orderService.query(customerId,waiterId,status);
        return MessageUtil.success("success",list);
    }

    @GetMapping("findAll")
    @ApiOperation("查询所有订单信息")
    public Message findAll(){
        List<Order> list = orderService.findAll();
        return MessageUtil.success("success",list);
    }

    @PostMapping("save")
    @ApiOperation("保存订单信息")
    public Message saveOrUpdate(@Valid @ModelAttribute OrderAndOrderLineVM order) throws Exception{
        orderService.save(order);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("deleteById")
    @ApiOperation("通过ID删除")
    public Message deleteById(@NotNull @RequestParam("id") Long id) throws Exception{
        orderService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除订单信息")
    public Message batchDelete(long[] ids) throws Exception{
        orderService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }
    @PostMapping("queryPage")
    @ApiOperation("分页查询订单信息")
    public Message queryPage(@NotNull @RequestParam("page") Integer page,
                             @NotNull @RequestParam("pageSize") Integer pageSize,
                         @ApiParam("订单时间") @RequestParam(required = false) Long orderTime,
                         @ApiParam("订单数量") @RequestParam(required = false) Double total,
                         @ApiParam("订单状态") @RequestParam(required = false) String status,
                         @ApiParam("顾客id") @RequestParam(required = false) Long customerId) throws Exception{
        Order order = new Order();
        order.setOrderTime(orderTime);
        order.setTotal(total);
        order.setStatus(status);
        order.setCustomerId(customerId);
        PageVM<Order> pageVM = orderService.queryPage(page,pageSize,order);
        return MessageUtil.success("操作成功",pageVM);
    }

    @GetMapping("sendOrder")
    @ApiOperation("派单")
    public Message sendOrder(
            @NotNull @RequestParam("waiterId") Long waiterId,
            @NotNull @RequestParam("orderId") Long  orderId) throws Exception{
        orderService.sendOrder(waiterId,orderId);
        return MessageUtil.success("派单成功");
    }

    @GetMapping("takeOrder")
    @ApiOperation("接单")
    public Message takeOrder(
            @NotNull @RequestParam("orderId") Long  orderId) throws Exception{
        orderService.takeOrder(orderId);
        return MessageUtil.success("接单成功");
    }

    @GetMapping("rejectOrder")
    @ApiOperation("拒绝订单")
    public Message rejectOrder(
            @NotNull @RequestParam("orderId") Long  orderId) throws Exception{
        orderService.rejectOrder(orderId);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("serviceCompleteOrder")
    @ApiOperation("员工服务结束")
    public Message serviceCompleteOrder(
            @NotNull @RequestParam("orderId") Long  orderId) throws Exception{
        orderService.serviceCompleted(orderId);
        return MessageUtil.success("服务完成");
    }

    @GetMapping("confirmOrder")
    @ApiOperation("确认订单")
    public Message confirmOrder(
            @NotNull @RequestParam("orderId") Long  orderId) throws Exception{
        orderService.confirmOrder(orderId);
        return MessageUtil.success("确认订单成功");
    }

    @GetMapping("cancelSendOrder")
    @ApiOperation("取消派单")
    public Message cancelSendOrder(
            @NotNull @RequestParam("orderId") Long  orderId) throws Exception{
        orderService.cancelSendOrder(orderId);
        return MessageUtil.success("取消成功");
    }

    @GetMapping("getOrderLinesByOrderId")
    @ApiOperation("通过订单编号查询订单项详情")
    public Message getOrderLinesByOrderId(
            @NotNull @RequestParam("orderId") Long  orderId) throws Exception{
        List<OrderLine> list = orderService.getOrderLinesByOrderId(orderId);
        return MessageUtil.success("取消成功",list);
    }
}
