package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.Product;
import com.briup.apps.ej.bean.extend.ProductExtend;
import com.briup.apps.ej.service.IProductService;
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

/**
 * @program: ej
 * @description: 产品控制器类
 * @author: charles
 * @create: 2019-06-10 17:52
 **/
@Api(description = "产品管理相关接口")
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("findAll")
    @ApiOperation("查询所有产品信息")
    public Message findAll(){
        List<Product> list = productService.findAll();
        return MessageUtil.success("success",list);
    }

    @GetMapping("findById")
    @ApiOperation("通过id查询产品信息")
    public Message findById(@NotNull @RequestParam("id") Long id){
        ProductExtend product = productService.findById(id);
        return MessageUtil.success("success",product);
    }

    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或者更新产品信息")
    public Message saveOrUpdate(@Valid @ModelAttribute Product product) throws Exception{
        productService.saveOrUpdate(product);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("deleteById")
    @ApiOperation("通过ID删除产品信息")
    public Message deleteById(@NotNull @RequestParam("id") Long id) throws Exception{
        productService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @GetMapping("findByCategoryId")
    @ApiOperation("通过ID删除产品信息")
    public Message findByCategoryId(@NotNull @RequestParam("id") Long id) throws Exception{
        List<Product> list = productService.findByCategoryId(id);
        return MessageUtil.success("success",list);
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除产品信息")
    public Message batchDelete(long[] ids) throws Exception{
        productService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }
    @PostMapping("query")
    @ApiOperation("分页查询产品信息")
    public Message query(@NotNull @RequestParam("page") Integer page,
                         @NotNull @RequestParam("pageSize") Integer pageSize,
                         @ApiParam("产品名称") @RequestParam(required = false) String name,
                         @ApiParam("产品描述") @RequestParam(required = false) String description,
                         @ApiParam("产品价格") @RequestParam(required = false) Double price,
                         @ApiParam("产品状态") @RequestParam(required = false) String status) throws Exception{
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStatus(status);
        PageVM<Product> pageVM = productService.query(page,pageSize,product);
        return MessageUtil.success("操作成功",pageVM);
    }

    @PostMapping("queryProductCascadeCategory")
    @ApiOperation("分页查询产品信息级联栏目")
    public Message queryProductCascadeCategory(@NotNull @RequestParam("page") Integer page,
                         @NotNull @RequestParam("pageSize") Integer pageSize,
                         @ApiParam("产品名称") @RequestParam(required = false) String name,
                         @ApiParam("产品描述") @RequestParam(required = false) String description,
                         @ApiParam("产品价格") @RequestParam(required = false) Double price,
                         @ApiParam("产品状态") @RequestParam(required = false) String status) throws Exception{
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStatus(status);
        PageVM<ProductExtend> pageVM = productService.queryProductCascadeCategory(page,pageSize,product);
        return MessageUtil.success("操作成功",pageVM);
    }

}
