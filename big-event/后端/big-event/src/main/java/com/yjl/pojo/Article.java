package com.yjl.pojo;


import com.yjl.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data
public class Article {
    public interface add{

    }
    public interface update{

    }
    @NotNull(groups ={update.class} )
    private Integer id;//主键ID
    @NotEmpty(groups = {add.class,update.class})
    @Pattern(regexp = "^\\S{1,10}$",groups = {add.class,update.class})
    private String title;//文章标题
    @NotEmpty(groups = {add.class,update.class})
    private String content;//文章内容
    @NotEmpty(groups = {add.class,update.class})
    @URL(groups = {add.class,update.class})
    private String coverImg;//封面图像
    @State(groups = {add.class,update.class})
    private String state;//发布状态 已发布|草稿
    @NotNull(groups = {add.class,update.class})
    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
