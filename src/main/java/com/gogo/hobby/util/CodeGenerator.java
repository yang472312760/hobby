package com.gogo.hobby.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CodeGenerator {

    /**
     * <p>
     * Description: 读取控制台内容
     * </p>
     *
     * @param tip 读取控制台内容
     * @return String 读取控制台内容
     */
    public static String scanner(String tip) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        log.info(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * <p>
     * Description: 代码生成器启动类
     * </p>
     *
     * @param args 默认参数
     */
    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //设置项目目录
        gc.setOutputDir(projectPath + "/src/main/java")
                //设置作者
                .setAuthor("yang").setOpen(false)
                //.setActiveRecord(true)
                //设置主键自增
                .setIdType(IdType.AUTO)
                //设置service层名称
                .setServiceName("%sService")
                //设置service层实现类的名称
                .setServiceImplName("%sServiceImpl")
                //设置mapper层名称
                .setMapperName("%sMapper")
                //设置xml 生成 BaseResultMap
                .setBaseResultMap(true)
                //设置xml 生成BaseColumnList
                .setBaseColumnList(true)

                .setDateType(DateType.ONLY_DATE)
                //开启swagger2
                .setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //数据库连接路径
        dsc.setUrl(
                "jdbc:mysql://192.168.220.128:3306/hobby?serverTimezone=GMT%2B8&useUnicode=true&passwordCharacterEncoding=utf-8&characterEncoding=utf-8");
        // dsc.setSchemaName("public");
        //数据库连接驱动
        dsc.setDriverName("com.mysql.jdbc.Driver");
        //数据库用户名
        dsc.setUsername("root");
        //数据库密码user
        dsc.setPassword("123456");

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        //pc.setModuleName("ba");
        //项目代码生成基础包
        pc.setParent("com.gogo.hobby.system");
        //controller层存放
        pc.setController("controller");
        //service层存放
        pc.setService("service");
        //service实现类存放
        pc.setServiceImpl("service.impl");
        //实体类存放
        pc.setEntity("model");
        //mapper接口存放
        pc.setMapper("mapper");
        //Map<String,String> map = new HashMap<>();
        //map.put("applicationName","demo");
        //pc.setPathInfo(map);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>(16);
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }

        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig templateConfig = buildTemplate();
        mpg.setTemplate(templateConfig);
        StrategyConfig strategy = buildStrategy();
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        gc.setFileOverride("y".equals(scanner("是否(y/n)需要覆盖文件")));
        mpg.setPackageInfo(pc);
        mpg.execute();
    }

    /**
     * <p>
     * Description: 配置模板
     * </p>
     *
     * @return TemplateConfig 模板配置
     */
    private static TemplateConfig buildTemplate() {
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setController("templates/controller.java");
        templateConfig.setXml(null);

        //templateConfig.setController("templates/controller.java");
        return templateConfig;
    }

    /**
     * <p>
     * Description: 策略配置
     * </p>
     *
     * @return StrategyConfig 策略配置
     */
    private static StrategyConfig buildStrategy() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperServiceClass("com.gogo.hobby.base.service.IBaseService");
        strategy.setSuperServiceImplClass("com.gogo.hobby.base.service.impl.BaseServiceImpl");
        strategy.setSuperMapperClass("com.gogo.hobby.base.mapper.IBaseMapper");
        strategy.setSuperEntityClass("com.gogo.hobby.base.model.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //strategy.setSuperEntityColumns(columns);
        strategy.setControllerMappingHyphenStyle(true);
        //"es_tm_","tt_","ts_","tm_","tc_","fak_","es_th","fak_th_","fak_tm_","ges_","prj_tm_","prj_tm_","prj_tr_"
        strategy.setTablePrefix(new String[] { "ba_", "sys_", "tc_", "tr_", "ts_", "tmp_", "bak_", "th_", "ti_", "tl_", "tm_", "tt_", "v_" });
        return strategy;
    }

}
