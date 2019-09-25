package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Comment;
import com.briup.apps.ej.bean.CommentExample;
import com.briup.apps.ej.dao.CommentMapper;
import com.briup.apps.ej.dao.extend.CommentExtendMapper;
import com.briup.apps.ej.service.ICommentService;
import com.briup.apps.ej.utils.PageVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ej
 * @description: 评论服务类
 * @author: charles
 * @create: 2019-09-06 15:12
 **/
@Service
public class CommentServiceImpl implements ICommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentExtendMapper commentExtendMapper;
    @Override
    public List<Comment> findAll() {
        return commentMapper.selectByExample(new CommentExample());
    }

    @Override
    public void saveOrUpdate(Comment comment) throws Exception {
        if(comment.getId()!=null){
            commentMapper.updateByPrimaryKey(comment);
        } else {
//            address.setStatus("正常");
            commentMapper.insert(comment);
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if(comment == null){
            throw new Exception("要删除的评论信息不存在");
        }
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) throws Exception {
        for(long id :ids){
            commentMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageVM<Comment> query(int page, int pageSize, Comment comment) {
        List<Comment> list = commentExtendMapper.query(page,pageSize,comment);
        long count = commentExtendMapper.count(comment);
        return new PageVM<>(page,pageSize,count,list);
    }
}
