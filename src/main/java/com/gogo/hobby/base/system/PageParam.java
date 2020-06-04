/**
 * PageParam.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.base.system;

import com.gogo.hobby.util.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ClassName: PageParam
 * </p>
 * <p>
 * Description: 分页请求参数
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年8月21日
 * </p>
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("分页参数")
public class PageParam {

    //--------------------通用分页参数-------------------------//
    /**
     * <p>
     * Description: 页码
     * </p>
     */
    @ApiModelProperty("页码")
    private int pageNum = 1;

    /**
     * <p>
     * Description: 分页大小
     * </p>
     */
    @ApiModelProperty("分页大小")
    private int pageSize = 30;

    //--------------------按下标偏移量分页----------------------//
    /**
     * <p>
     * Description: 起始记录
     * </p>
     */
    @ApiModelProperty("起始记录")
    private int start = 0;

    /**
     * <p>
     * Description: 记录数
     * </p>
     */
    @ApiModelProperty("记录数")
    private int limit = 20;

    /**
     * <p>
     * Description: 排序字段
     * </p>
     */
    @ApiModelProperty("排序字段")
    private String[] sort;

    private String orderBy;

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
        final StringBuilder STRING_BUILDER = new StringBuilder();

        for (int i = 0; i < sort.length; i++) {
            STRING_BUILDER.append("s.");
            if (sort[i].contains("-")) {
                STRING_BUILDER.append(StringUtil.umpToUnderline(sort[i].trim().replace("-", ""))).append(" desc");
            } else {
                STRING_BUILDER.append(StringUtil.umpToUnderline(sort[i]).trim()).append(" asc");
            }
            if (i < sort.length - 1) {
                STRING_BUILDER.append(",");
            }
        }
        this.orderBy = STRING_BUILDER.toString();
    }

    public String getOrderBy() {
        return orderBy;
    }

    /**
     *
     * <p>Description: TODO</p>
     * @param orderBy 排序
     */
    public void setOrderBy(String orderBy) {
        orderBy = StringUtil.umpToUnderline(orderBy);
        this.orderBy = orderBy;
    }
}
