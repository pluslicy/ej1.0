package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.BaseFile;

public interface IBaseFileService {

    /** 
     * @Description: 保存或更新附件记录
     * @Param: [baseFile] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-03-15 
     */ 
    void saveOrUpdate(BaseFile baseFile) throws Exception;

    /** 
     * @Description: 通过id删除
     * @Param: [id] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-03-16
     */ 
    void deleteById(String id) throws Exception;
}
