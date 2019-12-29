package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.BaseUser;
import com.briup.apps.ej.bean.Waiter;
import com.briup.apps.ej.service.IWaiterService;
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
 * @description: 服务员控制器类
 * @author: charles
 * @create: 2019-09-06 15:18
 **/
@Api(description = "服务员管理相关接口")
@Validated
@RestController
@RequestMapping("/waiter")
public class WaiterController {
    @Autowired
    private IWaiterService waiterService;

    @GetMapping("findAll")
    @ApiOperation("查询所有服务员信息")
    public Message findAll(){
        List<BaseUser> list = waiterService.findAll();
        return MessageUtil.success("success",list);
    }
    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或者更新服务员信息")
    public Message saveOrUpdate(BaseUser baseUser) throws Exception{
        waiterService.saveOrUpdate(baseUser);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("deleteById")
    @ApiOperation("通过ID删除服务员信息")
    public Message deleteById(@NotNull @RequestParam("id") Long id) throws Exception{
        waiterService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除服务员信息")
    public Message batchDelete(long[] ids) throws Exception{
        waiterService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }

    @Deprecated
    @PostMapping("query")
    @ApiOperation("分页查询员工信息")
    public Message query(@NotNull @RequestParam("page") Integer page,
                         @NotNull @RequestParam("pageSize") Integer pageSize,
                         @ApiParam("电话") @RequestParam(required = false) String telephone,
                         @ApiParam("姓名") @RequestParam(required = false) String realname,
                         @ApiParam("卡号") @RequestParam(required = false) String idcard,
                         @ApiParam("状态") @RequestParam(required = false) String status) throws Exception{
        Waiter waiter = new Waiter();
        waiter.setTelephone(telephone);
        waiter.setRealname(realname);
        waiter.setIdCard(idcard);
        waiter.setStatus(status);
        PageVM<Waiter> pageVM = waiterService.query(page,pageSize,waiter);
        return MessageUtil.success("操作成功",pageVM);
    }
}
