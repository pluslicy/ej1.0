package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.Address;
import com.briup.apps.ej.service.IAddressService;
import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("findByCustomerId")
    @ApiOperation("通过顾客ID查询地址信息")
    public Message findByCustomerId(@NotNull @RequestParam("id") Long id){
        List<Address> list = addressService.findByCustomerId(id);
        return MessageUtil.success("success",list);
    }
    @GetMapping("findAll")
    @ApiOperation("查询地址信息")
    public Message findAll(){
        List<Address> list = addressService.findAll();
        return MessageUtil.success("success",list);
    }
    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或者更新地址信息")
    public Message saveOrUpdate(@Valid @ModelAttribute Address address) throws Exception{
        addressService.saveOrUpdate(address);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("deleteById")
    @ApiOperation("通过ID删除地址信息")
    public Message deleteById(@NotNull @RequestParam("id") Long id) throws Exception{
        addressService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除地址信息")
    public Message batchDelete(long[] ids) throws Exception{
        addressService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }
}
