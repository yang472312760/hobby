package com.gogo.hobby.base.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>@ProjectName:bingo-framework-base</p>
 * <p>@Package:com.bingo.framework.base.model.system</p>
 * <p>@ClassName:UserLog</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/4/3 10:15</p>
 * <p>@Version:1.0</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_log")
public class UserLog {

    /**
     * <p>
     * Field serialVersionUID: 序列化
     * </p>
     */
    private static final long serialVersionUID = 4569472180384438538L;

    /**
     * <p>
     * Description: 应用名称
     * </p>
     */
    private String applicationName;

    /**
     * <p>
     * Description: 创建人ID
     * </p>
     */
    private Integer userId;

    /**
     * <p>
     * Description: 创建人
     * </p>
     */
    private String userName;

    /**
     * <p>
     * Description: 用户IP
     * </p>
     */
    private String userIp;

    /**
     * <p>
     * Description: 用户部门ID
     * </p>
     */
    private Integer deptId;

    /**
     * <p>
     * Description: 日志类型
     * </p>
     */
    private String logType;

    /**
     * <p>
     * Description: 日志内容
     * </p>
     */
    private String logContent;

    /**
     * <p>
     * Description: 操作类型
     * </p>
     */
    private String operateType;

    /**
     * <p>
     * Description: 创建时间
     * </p>
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * <p>
     * Description: 类名
     * </p>
     */
    private String className;

    /**
     * <p>
     * Description: 方法名
     * </p>
     */
    private String methodName;


    /**
     * <p>
     * Description: 操作时间
     * </p>
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateTime;

    /**
     * <p>
     * Description: 操作类型描述
     * </p>
     */
    private String operateTypeDesc;

    /**
     * <p>
     * Description: 操作者
     * </p>
     */
    private String operator;

    /**
     * <p>
     * Description: 项目ID
     * </p>
     */
    private String projectId;

}
