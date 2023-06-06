package com.ustc.oep.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.ustc.oep.handler.ListToStringTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-06-01 15:49
 */
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(MybatisConfiguration configuration) {
                TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
                registry.register(List.class, ListToStringTypeHandler.class);
            }
        };
    }
}
