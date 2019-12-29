package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.BaseUser;
import com.briup.apps.ej.bean.Customer;
import com.briup.apps.ej.service.ICustomerService;
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

@Api(description = "顾客管理相关接口")
@Validated
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;


    @GetMapping("findAll")
    @ApiOperation("查询所有顾客信息")
    public Message findAll(){
        List<BaseUser> list = customerService.findAll();
        return MessageUtil.success("success",list);
    }

    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或者更新顾客信息")
    public Message saveOrUpdate(@Valid @ModelAttribute BaseUser baseUser) throws Exception{
        customerService.saveOrUpdate(baseUser);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("deleteById")
    @ApiOperation("通过ID删除")
    public Message deleteById(@NotNull @RequestParam("id") Long id) throws Exception{
        customerService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @Deprecated
    @PostMapping("query")
    @ApiOperation("分页查询顾客信息")
    public Message query(@NotNull @RequestParam("page") Integer page,
                         @NotNull @RequestParam("pageSize") Integer pageSize,
                         @ApiParam("姓名") @RequestParam(required = false) String realname,
                         @ApiParam("手机号") @RequestParam(required = false) String telephone,
                         @ApiParam("状态") @RequestParam(required = false) String status) throws Exception{
        Customer customer = new Customer();
        customer.setRealname(realname);
        customer.setTelephone(telephone);
        customer.setStatus(status);
        PageVM<Customer> pageVM = customerService.query(page,pageSize,customer);
        return MessageUtil.success("操作成功",pageVM);
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除顾客信息")
    public Message batchDelete(long[] ids) throws Exception{
        customerService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }
}
