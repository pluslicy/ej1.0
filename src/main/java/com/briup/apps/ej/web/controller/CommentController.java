package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.bean.Comment;
import com.briup.apps.ej.service.ICommentService;
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
 * @description: 评论控制器类
 * @author: charles
 * @create: 2019-09-06 15:18
 **/
@Api(description = "评论管理相关接口")
@Validated
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("findAll")
    @ApiOperation("查询所有评论信息")
    public Message findAll(){
        List<Comment> list = commentService.findAll();
        return MessageUtil.success("success",list);
    }
    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或者更新评论信息")
    public Message saveOrUpdate(@Valid @ModelAttribute Comment comment) throws Exception{
        commentService.saveOrUpdate(comment);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("deleteById")
    @ApiOperation("通过ID删除评论信息")
    public Message deleteById(@NotNull @RequestParam("id") Long id) throws Exception{
        commentService.deleteById(id);
        return MessageUtil.success("删除成功");
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除评论信息")
    public Message batchDelete(long[] ids) throws Exception{
        commentService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }
    @PostMapping("query")
    @ApiOperation("分页查询评论信息")
    public Message query(@NotNull @RequestParam("page") Integer page,
                         @NotNull @RequestParam("pageSize") Integer pageSize,
                         @ApiParam("评论的内容") @RequestParam(required = false) String content,
                         @ApiParam("评论的时间") @RequestParam(required = false) Long commentTime) throws Exception {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCommentTime(commentTime);
        PageVM<Comment> pageVM = commentService.query(page, pageSize, comment);
        return MessageUtil.success("操作成功", pageVM);
    }
}
