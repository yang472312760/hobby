package com.gogo.hobby.base.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.hobby.constant.MessageConstans;
import com.gogo.hobby.constant.ReturnConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>@ProjectName:bingo-framework-base</p>
 * <p>@Package:com.bingo.framework.base.model.system</p>
 * <p>@ClassName:CommonResult</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/3/26 9:02</p>
 * <p>@Version:1.0</p>
 */
public class CommonResult<T> {

    /**
     * 200：成功；402：警告；500：错误
     */
    @Setter
    @Getter
    private Integer code = ReturnConstants.SUCCESS_CODE;

    @Getter
    @Setter
    private boolean success = true;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    @JsonIgnore
    private String messageCode = MessageConstans.OPERATION_SUCCESS;

    @Setter
    @Getter
    @JsonIgnore
    private String[] customizedMessage;

    @Getter
    @Setter
    private T data;

    @Getter
    @Setter
    @JsonIgnore
    private Boolean isCustomizedMessage = false;

    public CommonResult setSuccess() {
        return this;
    }

    public CommonResult setSuccess(T data) {
        this.data = data;
        return this;
    }

    public CommonResult setSuccess(String messageCode) {
        this.messageCode = messageCode;
        return this;
    }

    public CommonResult setSuccess(T data, String messageCode) {
        this.messageCode = messageCode;
        this.data = data;
        setValue(null, messageCode, null, data);
        return this;
    }

    public CommonResult setSuccess(String messageCode, String[] customizedMessage) {
        this.isCustomizedMessage = true;
        setValue(null, messageCode, customizedMessage, null);
        return this;
    }

    public CommonResult setSuccess(T data, String messageCode, String[] customizedMessage) {
        this.isCustomizedMessage = true;
        setValue(null, messageCode, customizedMessage, data);
        return this;
    }

    public CommonResult setSuccess(Integer code, T data, String messageCode, String[] customizedMessage) {
        this.isCustomizedMessage = true;
        setValue(code, messageCode, customizedMessage, data);
        return this;
    }

    public CommonResult setWarn(String messageCode) {
        this.success = false;
        setValue(ReturnConstants.WARN_CODE, messageCode, null, null);
        return this;
    }

    public CommonResult setWarn(String messageCode, T data) {
        this.success = false;
        setValue(ReturnConstants.WARN_CODE, messageCode, null, data);
        return this;
    }

    public CommonResult setWarn(String messageCode, String[] customizedMessage) {
        this.isCustomizedMessage = true;
        this.success = false;
        setValue(ReturnConstants.WARN_CODE, messageCode, customizedMessage, null);
        return this;
    }

    public CommonResult setWarn(String messageCode, String[] customizedMessage, T data) {
        this.isCustomizedMessage = true;
        this.success = false;
        setValue(ReturnConstants.WARN_CODE, messageCode, customizedMessage, data);
        return this;
    }

    public CommonResult setWarn(Integer code, String messageCode, String[] customizedMessage, T data) {
        this.isCustomizedMessage = true;
        this.success = false;
        setValue(code, messageCode, customizedMessage, data);
        return this;
    }

    public CommonResult setError(String messageCode) {
        this.success = false;
        setValue(ReturnConstants.ERROR_CODE, messageCode, null, null);
        return this;
    }

    public CommonResult setError(String messageCode, String[] customizedMessage) {
        this.isCustomizedMessage = true;
        this.success = false;
        setValue(ReturnConstants.ERROR_CODE, messageCode, customizedMessage, null);
        return this;
    }

    public CommonResult setError(String messageCode, T data) {
        this.success = false;
        setValue(ReturnConstants.ERROR_CODE, messageCode, null, data);
        return this;
    }

    public CommonResult setError(String messageCode, String[] customizedMessage, T data) {
        this.isCustomizedMessage = true;
        this.success = false;
        setValue(ReturnConstants.ERROR_CODE, messageCode, customizedMessage, data);
        return this;
    }

    public CommonResult setError(Integer code, String messageCode, String[] customizedMessage, T data) {
        this.isCustomizedMessage = true;
        this.success = false;
        setValue(code, messageCode, customizedMessage, data);
        return this;
    }

    private void setValue(Integer code, String messageCode, String[] customizedMessage, T data) {
        if (code != null) {
            this.code = code;
        }
        this.messageCode = messageCode;
        this.customizedMessage = customizedMessage;
        this.data = data;
    }
}
