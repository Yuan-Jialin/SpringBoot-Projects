package com.yjl.pojo;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;
@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//让SpringMVC把当前对象转换成json字符串的时候，忽略password，最终的json字符串中就没有password这个熟悉了
    private String password;//密码
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称
    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
