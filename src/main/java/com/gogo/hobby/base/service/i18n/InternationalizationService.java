/**
 * InternationalizationService.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.base.service.i18n;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * 
 * <p>
 * ClassName: InternationalizationService
 * </p>
 * <p>
 * Description: 国际化服务
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年10月9日
 * </p>
 * @author yang
 */
@Service
public class InternationalizationService {

    /**
     * <p>
     * Description: 消息源
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * 
     * <p>
     * Description: 构造器
     * </p>
     * 
     * @param messageSource 消息源
     */
    public InternationalizationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 
     * <p>
     * Description: 获取国际化
     * </p>
     * 
     * @param msgKey i18n前缀
     * @param args 参数
     * @return String i8n值
     */
    public String getMessage(String msgKey, Object[] args) {
        return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
    }

    /**
     * 
     * <p>
     * Description: 获取国际化
     * </p>
     * 
     * @param msgKey i18n前缀
     * @return String i8n值
     */
    public String getMessage(String msgKey) {
        Locale locale = LocaleContextHolder.getLocale();
        if (StringUtils.isBlank(msgKey)) {
            return "";
        } else {
            return messageSource.getMessage(msgKey, null, locale);
        }
    }

}
