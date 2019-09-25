package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.Address;
import com.briup.apps.ej.bean.extend.AddressExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressExtendMapper {

    List<AddressExtend> selectAll();

    List<Address> query(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("address") Address address);

    long count(@Param("address") Address address);
}
