package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Address;
import com.briup.apps.ej.bean.AddressExample;
import com.briup.apps.ej.dao.AddressMapper;
import com.briup.apps.ej.service.IAddressService;
import com.briup.apps.ej.utils.MessageUtil;
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

    @Override
    public List<Address> findAll() {
        return addressMapper.selectByExample(new AddressExample());
    }

    @Override
    public void saveOrUpdate(Address address) throws Exception {
        if(address.getId()!=null){
            addressMapper.updateByPrimaryKey(address);
        } else {
//            address.setStatus("正常");
            addressMapper.insert(address);
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        Address address = addressMapper.selectByPrimaryKey(id);
        if(address == null){
            throw new Exception("要删除的地址信息不存在");
        }
        addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            addressMapper.deleteByPrimaryKey(id);
        }
    }
}
