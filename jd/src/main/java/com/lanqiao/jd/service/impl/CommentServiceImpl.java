package com.lanqiao.jd.service.impl;


import com.lanqiao.jd.dao.CommentMapper;
import com.lanqiao.jd.dao.ProductItemMapper;
import com.lanqiao.jd.entity.Comment;
import com.lanqiao.jd.entity.Comment_User;
import com.lanqiao.jd.service.CommentService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ProductItemMapper productItemMapper;
    @Override
    public Result insertComment(Comment comment) {
        try{
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            comment.setCommentTime(dateFormat.format(date));
            System.out.println("service:"+comment.toString());
            int col = commentMapper.insertSelective(comment);
            System.out.println(col);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("插入数据库错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }
    //service:Comment(commentId=null, commentLevel=3, commentDetail=啊实打实, commentTime=2023-02-20 :04:37:23, productId=1, userId=2)
//service:Comment(commentId=null, commentLevel=4, commentDetail=啊实打实的, commentTime=2023-02-20 :04:36:48, productId=10, userId=2)
    @Override
    public Result showCommentByProductID(int productId) {
        try{

            List<Comment_User> commentList = commentMapper.selectByProductId(productId);
            return Result.createSuccessResult(commentList.size(),commentList);
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }
}
