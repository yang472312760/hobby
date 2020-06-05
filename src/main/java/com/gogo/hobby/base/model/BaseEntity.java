package com.gogo.hobby.base.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 4994778041235707664L;
    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @TableField("creator_id")
    private Integer creatorId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    @TableField("modifier_id")
    private Integer modifierId;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String modifier;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 是否删除
     */
    @JsonIgnore
    @ApiModelProperty(value = "是否删除")
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 是否临时
     */
    @JsonIgnore
    @ApiModelProperty(value = "是否临时")
    @TableField("is_temp")
    private Integer isTemp;

    /**
     * 系统Id
     */
    @JsonIgnore
    @ApiModelProperty(value = "系统ID")
    @TableField("sys_id")
    private Integer sysId;

    @Version
    @TableField("locking")
    private Integer locking;

    @JsonIgnore
    @TableField("locking_modify_time")
    private Date lockingModifyTime;

}
