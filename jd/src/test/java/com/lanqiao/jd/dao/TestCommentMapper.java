package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/2/20 16:40
 */
@SpringBootTest
public class TestCommentMapper {
    @Autowired
    CommentMapper commentMapper;
        @Test
        void TestInsert(){
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            commentMapper.insertSelective(new Comment( 4, "啊实打实的",dateFormat.format(date), 10, 2));
        }
}
