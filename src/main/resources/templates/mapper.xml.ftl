<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

    </#if>
    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
            <#list table.fields as field>
                <#if field.keyFlag>
                    <id column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
            <#list table.fields as field>
                <#if !field.keyFlag>
                    <result column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
            <#list table.commonFields as field>
                <result column="${field.name}" property="${field.propertyName}"/>
            </#list>
        </resultMap>

    </#if>
    <#if baseColumnList>
        <!-- 通用查询结果列 -->
        <sql id="Base_Column_List">
            <#list table.commonFields as field>
                s.${field.name},
            </#list>
            <#list table.fields as field>
                s.${field.name}<#if field_has_next>,</#if>
            </#list>
        </sql>
    </#if>

    <select id="queryPage" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${table.name} s
        <where>
            1 = 1
            <#list table.fields as field>
                <#if !field.keyFlag>
                    <if test="et.${field.propertyName} != null and ''!= et.${field.propertyName}">
                        and s.${field.name} = ${r'#{et.'}${field.propertyName}${r'}'}
                    </if>
                </#if>
            </#list>
            <#list table.commonFields as field>
                <#if !field.keyFlag>
                    <if test="et.${field.propertyName} != null and ''!= et.${field.propertyName}">
                        and s.${field.name} = ${r'#{et.'}${field.propertyName}${r'}'}
                    </if>
                </#if>
            </#list>
        </where>
    </select>

</mapper>
