/**
 * Page.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.base.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * ClassName: Paging
 * </p>
 * <p>
 * Description: 分页信息
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
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
@ApiModel("分页信息")
public class Page<T> implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: 序列化
     * </p>
     */
    private static final long serialVersionUID = -8654736706669970006L;

    /**
     * <p>
     * Description: 当前页
     * </p>
     */
    @ApiModelProperty("当前页")
    private int pageNum;

    /**
     * <p>
     * Description: 每页的数量
     * </p>
     */
    @ApiModelProperty("每页的数量")
    private int pageSize;

    /**
     * <p>
     * Description: 当前页的数量
     * </p>
     */
    @ApiModelProperty("当前页的数量")
    private int size;

    /**
     * <p>
     * Description: 排序
     * </p>
     */
    @ApiModelProperty("排序")
    private String orderBy;

    /**
     * <p>
     * Description: 当前页面第一个元素在数据库中的行号
     * </p>
     */
    @ApiModelProperty("当前页面第一个元素在数据库中的行号")
    private int startRow;

    /**
     * <p>
     * Description: 当前页面最后一个元素在数据库中的行号
     * </p>
     */
    @ApiModelProperty("当前页面最后一个元素在数据库中的行号")
    private int endRow;

    /**
     * <p>
     * Description: 总记录数
     * </p>
     */
    @ApiModelProperty("总记录数")
    private long total;

    /**
     * <p>
     * Description: 总页数
     * </p>
     */
    @ApiModelProperty("总页数")
    private int pages;

    /**
     * <p>
     * Description: 结果集
     * </p>
     */
    @ApiModelProperty("结果集")
    private List<T> rows;

    /**
     * <p>
     * Description: 前一页
     * </p>
     */
    @ApiModelProperty("前一页")
    private int prePage;

    /**
     * <p>
     * Description: 下一页
     * </p>
     */
    @ApiModelProperty("下一页")
    private int nextPage;

    /**
     * <p>
     * Description: 是否为第一页
     * </p>
     */
    @ApiModelProperty("是否为第一页")
    private boolean isFirstPage = false;

    /**
     * <p>
     * Description: 是否为最后一页
     * </p>
     */
    @ApiModelProperty("是否为最后一页")
    private boolean isLastPage = false;

    /**
     * <p>
     * Description: 是否有前一页
     * </p>
     */
    @ApiModelProperty("是否有前一页")
    private boolean hasPreviousPage = false;

    /**
     * <p>
     * Description: 是否有下一页
     * </p>
     */
    @ApiModelProperty("是否有下一页")
    private boolean hasNextPage = false;

    /**
     * <p>
     * Description: 导航页码数
     * </p>
     */
    @ApiModelProperty("导航页码数")
    private int navigatePages;

    /**
     * <p>
     * Description: 所有导航页号
     * </p>
     */
    @ApiModelProperty("所有导航页号")
    private int[] navigatepageNums;

    /**
     * <p>
     * Description: 导航条上的第一页
     * </p>
     */
    @ApiModelProperty("导航条上的第一页")
    private int navigateFirstPage;

    /**
     * <p>
     * Description: 导航条上的最后一页
     * </p>
     */
    @ApiModelProperty("导航条上的最后一页")
    private int navigateLastPage;

    /**
     * 
     * <p>
     * Description: 包装Page对象
     * </p>
     * 
     * @param list 数据集合
     */
    public Page(List<T> list) {
        this(list, 8);
    }
    public Page() {

    }
    /**
     * 
     * <p>
     * Description: 包装Page对象
     * </p>
     * 
     * @param list page结果
     * @param navigatePages 页码数量
     */
    public Page(List<T> list, int navigatePages) {
        if (list instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.orderBy = page.getOrderBy();

            this.pages = page.getPages();
            this.rows = page;
            this.size = page.size();
            this.total = page.getTotal();
            // 由于结果是>startRow的，所以实际的需要+1
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                // 计算实际的endRow（最后一页的时候特殊）
                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = 1;
            this.rows = list;
            this.size = list.size();
            this.total = list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }
        if (list instanceof Collection) {
            this.navigatePages = navigatePages;
            // 计算导航页
            calcNavigatepageNums();
            // 计算前后页，第一页，最后一页
            calcPage();
            // 判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 
     * <p>
     * Description: 计算导航页
     * </p>
     */
    private void calcNavigatepageNums() {
        // 当总页数小于或等于导航页码数时
        if (this.pages <= this.navigatePages) {
            this.navigatepageNums = new int[this.pages];
            for (int i = 0; i < this.pages; i++) {
                this.navigatepageNums[i] = i + 1;
            }
        } else { // 当总页数大于导航页码数时
            handleOther();
        }
    }

    /**
     * <p>Description: TODO</p>
     */
    private void handleOther() {
        this.navigatepageNums = new int[this.navigatePages];
        int startNum = this.pageNum - this.navigatePages / 2;
        int endNum = this.pageNum + this.navigatePages / 2;

        if (startNum < 1) {
            startNum = 1;
            // (最前navigatePages页
            for (int i = 0; i < this.navigatePages; i++) {
                this.navigatepageNums[i] = startNum++;
            }
        } else if (endNum > this.pages) {
            endNum = this.pages;
            // 最后navigatePages页
            for (int i = this.navigatePages - 1; i >= 0; i--) {
                this.navigatepageNums[i] = endNum--;
            }
        } else {
            // 所有中间页
            for (int i = 0; i < this.navigatePages; i++) {
                this.navigatepageNums[i] = startNum++;
            }
        }
    }

    /**
     * 
     * <p>
     * Description: 计算前后页，第一页，最后一页
     * </p>
     */
    private void calcPage() {
        if (this.navigatepageNums != null && this.navigatepageNums.length > 0) {
            this.navigateFirstPage = this.navigatepageNums[0];
            this.navigateLastPage = this.navigatepageNums[this.navigatepageNums.length - 1];
            if (this.pageNum > 1) {
                this.prePage = this.pageNum - 1;
            }
            if (this.pageNum < this.pages) {
                this.nextPage = this.pageNum + 1;
            }
        }
    }

    /**
     * 
     * <p>
     * Description: 判定页面边界
     * </p>
     */
    private void judgePageBoudary() {
        this.isFirstPage = this.pageNum == 1;
        this.isLastPage = this.pageNum == this.pages;
        this.hasPreviousPage = this.pageNum > 1;
        this.hasNextPage = this.pageNum < this.pages;
    }

    
}
