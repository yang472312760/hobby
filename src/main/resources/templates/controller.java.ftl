/**
 * ${table.controllerName}.java
 * Created at ${date}
 * Created by ${author}
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package ${package.Controller};

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${package.Mapper}.${table.mapperName};

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

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 *
 * <p>
 * ClassName: ${table.controllerName}
 * </p>
 * <p>
 * Description: ${table.comment!}控制器
 * </p>
 * <p>
 * Author: ${author}
 * </p>
 * <p>
 * Date: ${date}
 * </p>
 */
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
@Api(value = "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>", description = "${table.comment!}接口")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    /**
     * <p>
     * Description: ${table.comment!}服务
     * </p>
     */
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     *
     * <p>
     * Description: 分页查询
     * </p>
     *
     * @param paging 分页信息
     * @param ${entity?uncap_first} 查询条件
     * @return Result 分页数据
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.SELECT, valueIsTemplate = true, logContent = "分页查询${table.comment!},结果${r'${param2.success?string(\"成功\", \"失败\")}"'})
    @ApiOperation(value = "分页查询${table.comment!}", notes = "分页查询${table.comment!}")
    @GetMapping("/page")
    public CommonResult page(PageParam paging, ${entity} ${entity?uncap_first}) {
        List<${entity}> list = this.${table.serviceName?uncap_first}.queryPage(paging, ${entity?uncap_first});
        Page<${entity}> page = new Page<${entity}>(list);
        CommonResult result = new CommonResult().setSuccess(page);
        return result;
    }

    /**
     *
     * <p>
     * Description: 新增${table.comment!}
     * </p>
     *
     * @param ${entity?uncap_first} 插入${table.comment!}属性
     * @return Result 是否成功
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.INSERT, valueIsTemplate = true, logContent = "新增${table.comment!}--ID【${r'${param0.id!}'}】添加,结果${r'${param1.success?string(\"成功\", \"失败\")}"'})
    @ApiOperation(value = "新增${table.comment!}接口", notes = "新增${table.comment!}接口")
    @PostMapping()
    public CommonResult insert(@RequestBody ${entity} ${entity?uncap_first}) {
        CommonResult result = new CommonResult();
        Boolean isOk = this.${table.serviceName?uncap_first}.save(${entity?uncap_first});
        if (!isOk) {
            return result.setError(MessageConstans.SAVE_FAILURE);
        } else {
            return result.setSuccess(MessageConstans.SAVE_SUCCESS);
        }
    }

    /**
     *
     * <p>
     * Description: 修改${table.comment!}
     * </p>
     *
     * @param ${entity?uncap_first} 要修改的${table.comment!}，必须包含id
     * @return Result 修改是否成功
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.UPDATE, valueIsTemplate = true, logContent = "修改${table.comment!}--ID【${r'${param0.id!}'}】修改,结果${r'${param1.success?string(\"成功\", \"失败\")}"'})
    @ApiOperation(value = "修改${table.comment!}接口", notes = "修改${table.comment!}接口")
    @PutMapping()
    public CommonResult update(@RequestBody ${entity} ${entity?uncap_first}) {
    CommonResult result = new CommonResult();
        Boolean isOk = this.${table.serviceName?uncap_first}.updateById(${entity?uncap_first});
        if (!isOk) {
            return result.setError(MessageConstans.EDIT_FAILURE);
        } else {
            return result.setSuccess(MessageConstans.EDIT_SUCCESS);
        }
    }

    /**
     *
     * <p>
     * Description: 删除${table.comment!}
     * </p>
     *
     * @param id id 根据id删除${table.comment!}
     * @return Result 是否删除成功
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.DELETE, valueIsTemplate = true, logContent = "删除${table.comment!}--ID【${r'${param0!}'}】删除, 结果${r'${param1.success?string(\"成功\", \"失败\")}'}")
    @ApiOperation(value = "删除${table.comment!}接口", notes = "删除${table.comment!}接口")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Integer id) {
    CommonResult result = new CommonResult();
        Boolean isOk = this.${table.serviceName?uncap_first}.removeById(id);
        if (!isOk) {
            return result.setError(MessageConstans.DELETE_FAILURE);
        } else {
            return result.setSuccess(MessageConstans.DELETE_SUCCESS);
        }
    }


    /**
     *
     * <p>
     * Description: 根据ID获取${table.comment!}接口
     * </p>
     *
     * @param id 用户ID
     * @return CommonResult ${table.comment!}信息
     */
    @SystemLog(logType = LogType.BUSINESSLOG, operateType = OperateType.SELECT, valueIsTemplate = true, logContent = "根据ID获取${table.comment!}--ID【${r'${param0!}'}】, 结果${r'${param1.success?string(\"成功\", \"失败\")}'}")
    @ApiOperation(value = "根据ID获取${table.comment!}接口", notes = "根据ID获取${table.comment!}接口")
    @ApiImplicitParam(name = "id", value = "${table.comment!}信息id", required = true, dataType = "Int", paramType = "path")
    @GetMapping("/{id}")
    public CommonResult get${entity}ById(@PathVariable Integer id) {
    CommonResult result = new CommonResult();
        ${entity} ${entity?uncap_first} = this.${table.serviceName?uncap_first}.getById(id);
        if (${entity?uncap_first} != null) {
            return result.setSuccess(${entity?uncap_first});
        }
        return result.setError(MessageConstans.NOT_FIND_VALUE);
    }


}
</#if>
 