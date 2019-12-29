package com.briup.apps.ej.bean.extend;

import com.briup.apps.ej.bean.*;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.List;

public class OrderExtend extends Order {
    public static final String STATUS_DAIZHIFU = "待支付";
    public static final String STATUS_DAIPAIDAN = "待派单";
    public static final String STATUS_DAIJIEDAN = "待接单";
    public static final String STATUS_DAIFWU = "待服务";
    public static final String STATUS_DAIQUEREN = "待确认";
    public static final String STATUS_COMPLETE = "已完成";


    private Customer customer;
    private Waiter waiter;
    private Address address;
    private List<OrderLine> orderLines;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
