package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.BaseUser;

public interface IBaseUserService {
    BaseUser findUser(String username, String password, String type);
}
