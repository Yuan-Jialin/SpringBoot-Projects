package com.example.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/20 16:55
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /*
     * 插入操作自动填充
     * */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", new Long(1));
        metaObject.setValue("updateUser", new Long(1));
    }

    /*
     * 更新操作自动填充
     * */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updata");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", new Long(1));

    }
}
