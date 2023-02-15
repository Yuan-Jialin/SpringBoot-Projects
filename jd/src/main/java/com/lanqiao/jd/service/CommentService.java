package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.Comment;
import com.lanqiao.jd.util.Result;

public interface CommentService {

    //插入评论
    public Result insertComment(Comment comment);

    //展示评论
    public Result showCommentByProductID(int productId);
}
