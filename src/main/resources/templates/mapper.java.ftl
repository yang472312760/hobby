/**
 * ${entity}Mapper.java
 * Created at ${date}
 * Created by ${author}
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package ${package.Mapper};

import java.util.List;

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * ClassName: ${table.mapperName}
 * </p>
 * <p>
 * Description: ${table.comment!} Mapper 接口
 * </p>
 * <p>
 * Author: ${author}
 * </p>
 * <p>
 * Date: ${date}
 * </p>
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@Repository
@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
