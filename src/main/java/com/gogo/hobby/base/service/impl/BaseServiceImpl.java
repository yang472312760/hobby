package com.gogo.hobby.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gogo.hobby.base.mapper.IBaseMapper;
import com.gogo.hobby.base.service.IBaseService;

import com.github.pagehelper.PageHelper;

import java.util.List;

import com.gogo.hobby.base.system.PageParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 */
public class BaseServiceImpl<M extends IBaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    /**
     *
     * <p>
     * Description: baseMapper
     * </p>
     */
    @Autowired
    protected M baseMapper;


    @Override
    public List<T> queryPage(PageParam pageParam, T t) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), pageParam.getOrderBy());
        return this.baseMapper.queryPage(pageParam, t);
    }

    @Override
    public List<T> offsetPage(PageParam pageParam, T t) {
        PageHelper.offsetPage(pageParam.getStart(), pageParam.getLimit());
        PageHelper.orderBy(pageParam.getOrderBy());
        return this.baseMapper.queryPage(pageParam, t);
    }

}
