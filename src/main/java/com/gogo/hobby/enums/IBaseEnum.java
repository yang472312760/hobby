/**
 * BaseEnum.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.enums;

/**
 * 
 * <p>
 * ClassName: IBaseEnum
 * </p>
 * <p>
 * Description: 基础枚举接口
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月11日
 * </p>
 * @author yang
 */
public interface IBaseEnum<K> {

    /**
     * <p>
     * Description: 获取id
     * </p>
     *
     * @return K 泛型
     */
    K getIdentity();

    /**
     * <p>
     * Description: 描述
     * </p>
     *
     * @return String 描述
     */
    String getLiteralName();
}
