/**
 * ${entity}Service.java
 * Created at ${date}
 * Created by ${author}
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package ${package.Service};


import ${package.Entity}.${entity};
import ${superServiceClassPackage};




/**
 *
 * <p>
 * ClassName: ${entity}
 * </p>
 * <p>
 * Description: ${table.comment!} 服务类
 * </p>
 * <p>
 * Author: ${author}
 * </p>
 * <p>
 * Date: ${date}
 * </p>
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
