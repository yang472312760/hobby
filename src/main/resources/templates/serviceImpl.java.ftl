/**
 * ${entity}ServiceImpl.java
 * Created at ${date}
 * Created by ${author}
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

import org.apache.ibatis.annotations.Param;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * <p>
 * ClassName: ${table.serviceImplName}
 * </p>
 * <p>
 * Description: ${table.comment!} 服务实现类
 * </p>
 * <p>
 * Author: ${author}
 * </p>
 * <p>
 * Date: ${date}
 * </p>
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {


}
</#if>
