package com.ustc.oep.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:04
 */
@Component
@Slf4j
public class MetaObjectHandler implements com.baomidou.mybatisplus.core.handlers.MetaObjectHandler {
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共自动填充【update】");
        metaObject.setValue("lastModifiedTime", LocalDateTime.now());
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共自动填充【insert】");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("lastModifiedTime", LocalDateTime.now());
    }
}
