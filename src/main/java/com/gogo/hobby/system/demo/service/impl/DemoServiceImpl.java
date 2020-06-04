/**
 * DemoServiceImpl.java
 * Created at 2020-06-04
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.demo.service.impl;

import com.gogo.hobby.system.demo.model.Demo;
import com.gogo.hobby.system.demo.mapper.DemoMapper;
import com.gogo.hobby.system.demo.service.DemoService;
import com.gogo.hobby.base.service.impl.BaseServiceImpl;
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
 * ClassName: DemoServiceImpl
 * </p>
 * <p>
 * Description:  服务实现类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2020-06-04
 * </p>
 */
@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoMapper, Demo> implements DemoService {


}
