/**
 * MybatisPlusConfig.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * 
 * <p>
 * ClassName: MybatisPlusConfig
 * </p>
 * <p>
 * Description: mybatis plus 配置文件
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月11日
 * </p>
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 
     * <p>
     * Description: 注入分页插件
     * </p>
     * 
     * @return PaginationInterceptor 注入分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 
     * <p>
     * Description: 乐观锁插件
     * </p>
     * 
     * @return  OptimisticLockerInterceptor 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
