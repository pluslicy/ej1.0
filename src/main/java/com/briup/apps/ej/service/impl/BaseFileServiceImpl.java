package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.BaseFile;
import com.briup.apps.ej.dao.BaseFileMapper;
import com.briup.apps.ej.service.IBaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseFileServiceImpl implements IBaseFileService {

    @Autowired
    private BaseFileMapper baseFileMapper;

    @Override
    public void saveOrUpdate(BaseFile baseFile) throws Exception {
        if(baseFile.getId()!=null){
            baseFileMapper.updateByPrimaryKey(baseFile);
        } else {
            baseFileMapper.insert(baseFile);
        }
    }

    @Override
    public void deleteById(String id) throws Exception {
        baseFileMapper.deleteByPrimaryKey(id);
    }
}
