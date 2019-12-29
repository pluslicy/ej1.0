package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.BaseUser;
import com.briup.apps.ej.service.IBaseUserService;
import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import com.briup.apps.ej.vm.UserVM;
import com.briup.apps.ej.vm.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: ej
 * @description: 用户类
 * @author: charles
 * @create: 2019-10-25 13:19
 **/
@Api(description = "用户管理相关接口")
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IBaseUserService baseUserService;
    @Autowired
    private ServletContext context;

    @PostMapping(path="login")
    @ApiOperation("登录")
    public Message login( @RequestBody UserVM userVM) throws Exception{
        BaseUser user = baseUserService.findUser(userVM.getUsername(),userVM.getPassword(), userVM.getType());
        if( user != null ){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            context.setAttribute(uuid,user);
            Map<String,String> map = new HashMap<>();
            map.put("token",uuid);
            return MessageUtil.success("success",map);
        } else {
            return MessageUtil.error("用户信息不符");
        }
        // 消息验证
    }

    @GetMapping("info")
    @ApiOperation("根据token获取用户信息")
    public Message info( String token) throws Exception{
        Object obj = context.getAttribute(token);
        if(obj!=null && obj instanceof BaseUser){
            BaseUser user = (BaseUser) obj;
            String[] roles = new String[]{"admin"};

            UserInfo info = new UserInfo(user.getId(),user.getUsername(),"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif","superUser",roles);
            return MessageUtil.success("success",info);
        }
        return MessageUtil.error("token失效，请重新登录");

    }

    @PostMapping("logout")
    @ApiOperation("退出")
    public Message logout() throws Exception{
        return MessageUtil.success("success");
    }



}
