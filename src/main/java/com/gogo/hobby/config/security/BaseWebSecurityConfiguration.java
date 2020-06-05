/**
 * BaseWebSecurityConfiguration.java
 * Created at 2019-7-30
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.security;

import com.gogo.hobby.filter.JsonWebTokenAuthenticationFilter;
import com.gogo.hobby.filter.JsonWebTokenAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * <p>
 * ClassName: BaseWebSecurityConfiguration
 * </p>
 * <p>
 * Description: Spring security配置类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 * @author Administrator
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class BaseWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 验证登陆
     */
    @Autowired
    private AjaxAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 验证权限
     */
    @Autowired
    private BaseAuthenticationProvider authenticationProvider;

    /**
     * 权限不足处理器
     */
    @Autowired
    private AjaxAccessDeniedHandler accessDeniedHandler;

    /**
     * 登出成功
     */
    @Autowired
    private AjaxLogoutSuccessHandler logoutSuccessHandler;

    /**
     * 验证失败
     */
    @Autowired
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 验证成功
     */
    @Autowired
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().authenticationEntryPoint(this.authenticationEntryPoint).and()
                .authorizeRequests().antMatchers("/druid/*").permitAll().antMatchers("/druid/css/*").permitAll()
                .antMatchers("/druid/js/*").permitAll().antMatchers("/dept/*").permitAll().antMatchers("/depts")
                .permitAll().antMatchers("/book/*").permitAll().antMatchers("/swagger*").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/css/*").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/lib/*").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/*").permitAll().antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/actuator").permitAll().antMatchers("/actuator/*").permitAll().anyRequest()
                .authenticated().and().addFilter(new JsonWebTokenAuthenticationFilter(authenticationManager()))
                .addFilter(new JsonWebTokenAuthorizationFilter(authenticationManager())).formLogin()
                .loginProcessingUrl("/login").successHandler(this.authenticationSuccessHandler)
                .failureHandler(this.authenticationFailureHandler).permitAll().and().logout()
                .deleteCookies("hobby").clearAuthentication(true)
                .logoutSuccessHandler(this.logoutSuccessHandler).permitAll().and().exceptionHandling()
                .accessDeniedHandler(this.accessDeniedHandler).and().cors();
    }

    /**
     * 
     * <p>
     * Description: 密码加密
     * </p>
     * 
     * @return PasswordEncoder 棉麻加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

}
