package com.gogo.hobby.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gogo.hobby.base.system.PageParam;

import java.util.List;

/**
 * @author Administrator
 */
public interface IBaseService<T> extends IService<T> {

    /**
     *
     * <p>
     * Description: 分页查询(按照页码及分页大小分页)
     * </p>
     *
     * @param pageParam 分页信息
     * @param t 实体类
     * @return List<T> 数据集合
     */
    List<T> queryPage(PageParam pageParam, T t);

    /**
     *
     * <p>Description: 分页查询(按照起始下标及偏移量分页)</p>
     * @param pageParam 分页信息
     * @param t 实体类
     * @return List<T> 数据集合
     */
    List<T> offsetPage(PageParam pageParam, T t);

}
