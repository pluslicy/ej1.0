package com.briup.apps.ej.service;

import com.briup.apps.ej.bean.Comment;
import com.briup.apps.ej.utils.PageVM;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll();

    void saveOrUpdate(Comment comment) throws Exception;

    void deleteById(long id) throws Exception;

    void batchDelete(long[] ids) throws Exception;

    PageVM<Comment> query(int page, int pageSize, Comment comment);
}
