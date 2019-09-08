package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Address;

import java.util.List;

public interface IAddressService {
    List<Address> findByCustomerId(long id);
}
