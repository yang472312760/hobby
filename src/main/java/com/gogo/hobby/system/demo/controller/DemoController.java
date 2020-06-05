/**
 * DemoController.java
 * Created at 2020-06-04
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.demo.controller;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.gogo.hobby.system.demo.model.Demo;
import com.gogo.hobby.system.demo.service.DemoService;
import com.gogo.hobby.system.demo.mapper.DemoMapper;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;

import com.gogo.hobby.annotation.SystemLog;
import com.gogo.hobby.enums.system.LogType;
import com.gogo.hobby.enums.system.OperateType;
import com.gogo.hobby.base.system.PageParam;
import com.gogo.hobby.base.system.Page;
import com.gogo.hobby.base.system.CommonResult;
import com.gogo.hobby.constant.MessageConstans;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * <p>
 * ClassName: DemoController
 * </p>
 * <p>
 * Description: 控制器
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2020-06-04
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/demo/demo")
@Api(value = "/demo/demo", description = "接口")
public class DemoController {

    /**
     * <p>
     * Description: 服务
     * </p>
     */
    @Autowired
    private DemoService demoService;

    /**
     *
     * <p>
     * Description: 分页查询
     * </p>
     *
     * @param paging 分页信息
     * @param demo 查询条件
     * @return Result 分页数据
     */
    @PreAuthorize("hasRole('admin') or hasPermission('demo','select')")
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.SELECT, valueIsTemplate = true, logContent = "分页查询,结果${param2.success?string(\"成功\", \"失败\")}")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public CommonResult page(PageParam paging, Demo demo) {
        List<Demo> list = this.demoService.queryPage(paging, demo);
        Page<Demo> page = new Page<Demo>(list);
        CommonResult result = new CommonResult().setSuccess(page);
        return result;
    }

    /**
     *
     * <p>
     * Description: 新增
     * </p>
     *
     * @param demo 插入属性
     * @return Result 是否成功
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.INSERT, valueIsTemplate = true, logContent = "新增--ID【${param0.id!}】添加,结果${param1.success?string(\"成功\", \"失败\")}")
    @ApiOperation(value = "新增接口", notes = "新增接口")
    @PostMapping()
    public CommonResult insert(@RequestBody Demo demo) {
        CommonResult result = new CommonResult();
        Boolean isOk = this.demoService.save(demo);
        if (!isOk) {
            return result.setError(MessageConstans.SAVE_FAILURE);
        } else {
            return result.setSuccess(MessageConstans.SAVE_SUCCESS);
        }
    }

    /**
     *
     * <p>
     * Description: 修改
     * </p>
     *
     * @param demo 要修改的，必须包含id
     * @return Result 修改是否成功
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.UPDATE, valueIsTemplate = true, logContent = "修改--ID【${param0.id!}】修改,结果${param1.success?string(\"成功\", \"失败\")}")
    @ApiOperation(value = "修改接口", notes = "修改接口")
    @PutMapping()
    public CommonResult update(@RequestBody Demo demo) {
    CommonResult result = new CommonResult();
        Boolean isOk = this.demoService.updateById(demo);
        if (!isOk) {
            return result.setError(MessageConstans.EDIT_FAILURE);
        } else {
            return result.setSuccess(MessageConstans.EDIT_SUCCESS);
        }
    }

    /**
     *
     * <p>
     * Description: 删除
     * </p>
     *
     * @param id id 根据id删除
     * @return Result 是否删除成功
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.DELETE, valueIsTemplate = true, logContent = "删除--ID【${param0!}】删除, 结果${param1.success?string(\"成功\", \"失败\")}")
    @ApiOperation(value = "删除接口", notes = "删除接口")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Integer id) {
    CommonResult result = new CommonResult();
        Boolean isOk = this.demoService.removeById(id);
        if (!isOk) {
            return result.setError(MessageConstans.DELETE_FAILURE);
        } else {
            return result.setSuccess(MessageConstans.DELETE_SUCCESS);
        }
    }


    /**
     *
     * <p>
     * Description: 根据ID获取接口
     * </p>
     *
     * @param id 用户ID
     * @return CommonResult 信息
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.SELECT, valueIsTemplate = true, logContent = "根据ID获取--ID【${param0!}】, 结果${param1.success?string(\"成功\", \"失败\")}")
    @ApiOperation(value = "根据ID获取接口", notes = "根据ID获取接口")
    @ApiImplicitParam(name = "id", value = "信息id", required = true, dataType = "Int", paramType = "path")
    @GetMapping("/{id}")
    public CommonResult getDemoById(@PathVariable Integer id) {
    CommonResult result = new CommonResult();
        Demo demo = this.demoService.getById(id);
        if (demo != null) {
            return result.setSuccess(demo);
        }
        return result.setError(MessageConstans.NOT_FIND_VALUE);
    }


}
