package com.yjl.mapper;

import com.yjl.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into  article(title,content,cover_img,state,category_id,create_user,create_time,update_time) values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(String categoryId, String state, Integer userId);
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId} where id=#{id}")
    void update(Article article);
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);
    @Delete("delete from article where id =#{id}")
    void delete(Integer id);
}
