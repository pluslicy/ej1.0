package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.BaseUser;
import com.briup.apps.ej.bean.BaseUserExample;
import com.briup.apps.ej.dao.BaseUserMapper;
import com.briup.apps.ej.service.IBaseUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ej
 * @description: 用户服务类
 * @author: charles
 * @create: 2019-10-28 11:22
 **/
@Service
public class BaseUserServiceImpl implements IBaseUserService {
    @Resource
    private BaseUserMapper baseUserMapper;
    @Override
    public BaseUser findUser(String username, String password, String type) {
        BaseUserExample example = new BaseUserExample();
        BaseUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        criteria.andTypeEqualTo(type);
        List<BaseUser> list = baseUserMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
