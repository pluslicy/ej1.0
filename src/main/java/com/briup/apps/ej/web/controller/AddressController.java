package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.Address;
import com.briup.apps.ej.service.IAddressService;
import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: ej
 * @description: 地址控制器类
 * @author: charles
 * @create: 2019-09-06 15:17
 **/
@Api(description = "地址管理相关接口")
@Validated
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @GetMapping("findAll")
    @ApiOperation("查询所有地址信息")
    public Message findByCustomerId(@NotNull @RequestParam("id") Long id){
        List<Address> list = addressService.findByCustomerId(id);
        return MessageUtil.success("success",list);
    }
}
