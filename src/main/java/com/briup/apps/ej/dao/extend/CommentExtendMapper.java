package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentExtendMapper {
    List<Comment> query(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("comment") Comment comment);

    long count(@Param("comment") Comment comment);
}
