package com.gogo.hobby.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gogo.hobby.base.system.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yang
 */
public interface IBaseMapper<T> extends BaseMapper<T> {

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
    List<T> queryPage(@Param("page") PageParam pageParam, @Param("et") T t);

    /**
     *
     * <p>Description: 分页查询(按照起始下标及偏移量分页)</p>
     * @param pageParam 分页信息
     * @param t 实体类
     * @return List<T> 数据集合
     */
    List<T> offsetPage(@Param("page") PageParam pageParam, @Param("et") T t);


}
