package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Address;
import com.briup.apps.ej.bean.AddressExample;
import com.briup.apps.ej.dao.AddressMapper;
import com.briup.apps.ej.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ej
 * @description: 地址服务类
 * @author: charles
 * @create: 2019-09-06 15:11
 **/
@Service
public class AddressServiceImpl implements IAddressService {
    @Resource
    private AddressMapper addressMapper;

    @Override
    public List<Address> findByCustomerId(long id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andCustomerIdEqualTo(id);
        return addressMapper.selectByExample(addressExample);
    }
}
