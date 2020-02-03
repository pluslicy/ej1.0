package com.briup.apps.ej.bean.extend;

import com.briup.apps.ej.bean.OrderLine;
import com.briup.apps.ej.bean.Product;

/**
 * @program: ej
 * @description: 订单拓展
 * @author: charles
 * @create: 2020-01-07 16:32
 **/

public class OrderLineExtend extends OrderLine {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
