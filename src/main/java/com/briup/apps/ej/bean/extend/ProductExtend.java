package com.briup.apps.ej.bean.extend;

import com.briup.apps.ej.bean.Category;
import com.briup.apps.ej.bean.Product;

/**
 * @program: ej
 * @description: 产品的扩展类
 * @author: charles
 * @create: 2019-09-30 09:23
 **/

public class ProductExtend extends Product {
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
