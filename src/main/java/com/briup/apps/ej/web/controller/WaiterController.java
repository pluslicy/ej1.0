package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.Waiter;
import com.briup.apps.ej.service.IWaiterService;
import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Waiter> list = waiterService.findAll();
        return MessageUtil.success("success",list);
    }
}
