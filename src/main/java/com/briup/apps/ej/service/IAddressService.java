package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Address;
import com.briup.apps.ej.bean.extend.AddressExtend;
import com.briup.apps.ej.utils.PageVM;

import java.util.List;

public interface IAddressService {
    List<Address> findByCustomerId(long id);

    List<Address> findAll();

    List<AddressExtend> findAllAddressWithCustomer();

    PageVM<Address> query(int page, int pageSize, Address address);

    void saveOrUpdate(Address address) throws Exception;

    void deleteById(long id) throws Exception;

    void batchDelete(long[] ids) throws Exception;
}
