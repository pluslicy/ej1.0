package com.briup.apps.ej.dao.extend;

import com.briup.apps.ej.bean.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryExtendMapper {
    List<Category> query(
            @Param("page") int page,
            @Param("pageSize") int pageSize,
            @Param("category") Category category);

    long count(@Param("category") Category category);
}
