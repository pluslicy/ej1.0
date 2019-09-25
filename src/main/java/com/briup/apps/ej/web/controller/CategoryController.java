package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.Category;
import com.briup.apps.ej.service.ICategoryService;
import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import com.briup.apps.ej.utils.PageVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: ej
 * @description: 栏目控制器类
 * @author: charles
 * @create: 2019-09-06 15:17
 **/
@Api(description = "栏目管理相关接口")
@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("findAll")
    @ApiOperation("查询所有栏目信息")
    public Message findAll(){
        List<Category> list = categoryService.findAll();
        return MessageUtil.success("success",list);
    }
    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或者更新分类信息")
    public Message saveOrUpdate(@Valid @ModelAttribute Category category) throws Exception{
        categoryService.saveOrUpdate(category);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("deleteById")
    @ApiOperation("通过ID删除分类信息")
    public Message deleteById(@NotNull @RequestParam("id") Long id) throws Exception{
        categoryService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除分类信息")
    public Message batchDelete(long[] ids) throws Exception{
        categoryService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }
    @PostMapping("query")
    @ApiOperation("分页查询分类信息")
    public Message query(@NotNull @RequestParam("page") Integer page,
                         @NotNull @RequestParam("pageSize") Integer pageSize,
                         @ApiParam("名称") @RequestParam(required = false) String name,
                         @ApiParam("数量") @RequestParam(required = false) Integer num) throws Exception {
        Category category = new Category();
        category.setName(name);
        category.setNum(num);
        PageVM<Category> pageVM = categoryService.query(page, pageSize, category);
        return MessageUtil.success("操作成功", pageVM);
    }
}
