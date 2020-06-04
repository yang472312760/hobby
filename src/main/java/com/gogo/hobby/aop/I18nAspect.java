package com.gogo.hobby.aop;

import com.gogo.hobby.base.service.i18n.InternationalizationService;
import com.gogo.hobby.base.system.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * <p>@ProjectName:bingo-framework-base</p>
 * <p>@Package:com.bingo.framework.base.aop</p>
 * <p>@ClassName:I18nAspect</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/3/27 16:51</p>
 * <p>@Version:1.0</p>
 */
@Aspect
@Component
@Slf4j
@ConditionalOnExpression("${hobby.i18n.enabled}")
public class I18nAspect {

    @Autowired
    private InternationalizationService internationalizationService;

    @AfterReturning(pointcut = "execution (* com.gogo.hobby..controller.*Controller.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, final CommonResult result) {
        if (result != null) {
            if (result.getIsCustomizedMessage() && result.getCustomizedMessage().length > 0) {
                result.setMessage(this.replaceMessage(internationalizationService.getMessage(result.getMessageCode()),
                        result.getCustomizedMessage()));
            } else {
                result.setMessage(internationalizationService.getMessage(result.getMessageCode()));
            }
        }
    }

    private String replaceMessage(String tempMessage, String[] replaceMessage) {
        for (int i = 0; i < replaceMessage.length; i++) {
            tempMessage = tempMessage.replace("{" + (i + 1) + "}", replaceMessage[i]);
        }
        return tempMessage;
    }

}
