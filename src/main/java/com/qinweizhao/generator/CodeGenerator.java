package com.qinweizhao.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.qinweizhao.config.GeneratorConfig;

/**
 * @author qinweizhao
 * @since 2021/10/28
 */
public class CodeGenerator {

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generator();
    }



    /**
     * 生成
     */
    public void generator() {
        AutoGenerator generator = new AutoGenerator(GeneratorConfig.DATA_SOURCE_CONFIG);
        // 全局配置
        generator.global(GeneratorConfig.globalConfig());
        // 包配置
        generator.packageInfo(GeneratorConfig.packageConfig());
        // 策略配置
        generator.strategy(GeneratorConfig.strategyConfig());
        // 模板配置
        generator.template(GeneratorConfig.templateConfig());

        // 执行
        generator.execute(new FreemarkerTemplateEngine());
    }



    /**
     * public class GeneratorConfig {
     * <p>
     * 数据源配置
     */
    public static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/eadmin?serverTimezone=UTC", "root", "112121")
            // 可选-> 数据库查询
//            .dbQuery(new MySqlQuery())
            // 可选-> 数据库schema(部分数据库适用)
//            .schema("mybatis-plus")
            // 可选-> 数据库类型转换器
//            .typeConvert(new MySqlTypeConvert())
            // 可选-> 数据库关键字处理器
//            .keyWordsHandler(new MySqlKeyWordsHandler())

            .build();


    private GeneratorConfig() {
    }

    /**
     * 全局配置
     */
    public static GlobalConfig globalConfig() {
        return new GlobalConfig.Builder()
                // 覆盖已生成文件
                .fileOverride()
                // 禁止打开输出目录
//                .disableOpenDir()
                // 指定输出目录
                .outputDir("/opt/baomidou")
                // 作者名
                .author("baomidou")
                // 开启 kotlin 模式
                .enableKotlin()
                // 开启 swagger 模式
                .enableSwagger()
                // 时间策略
                .dateType(DateType.TIME_PACK)
                // 注释日期
                .commentDate("yyyy-MM-dd")

                .build();
    }

    /**
     * 包配置
     */
    public static PackageConfig packageConfig() {
        return new PackageConfig.Builder()
                // 父包名
                .parent("com.qinweizhao")
                // 父包模块名
                .moduleName("sys")
                // Entity 包名
                .entity("po")
                // Service 包名
                .service("service")
                // Service Impl 包名
                .serviceImpl("service.impl")
                // Mapper 包名
                .mapper("mapper")
                // Controller 包名
                .controller("controller")
                // 自定义文件包名
                .other("other")
                // 路径配置信息
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://"))
                .build();
    }


    /**
     * 模板配置
     */
    public static TemplateConfig templateConfig() {
        return new TemplateConfig.Builder()
                // 禁用所有模板
                //  .disable()
                // 禁用模板
//                .disable(TemplateType.ENTITY)
                // 设置实体模板路径(JAVA)
                .entity("/templates/entity.java")
                // 设置实体模板路径(kotlin)
//                .entityKt("/templates/entity.java")
                // 设置 service 模板路径
                .service("/templates/service.java")
                // 设置 serviceImpl 模板路径
                .serviceImpl("/templates/serviceImpl.java")
                // 设置 mapper 模板路径
                .mapper("/templates/mapper.java")
                // 设置 mapperXml 模板路径
                .mapperXml("/templates/mapper.xml")
                // 设置 controller 模板路径
                .controller("/templates/controller.java")
                .build();
    }

    /**
     * 注入配置
     */
    public static InjectionConfig injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder()
                // 输出文件之前消费者
                .beforeOutputFile((tableInfo, objectMap) -> {
                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                })
                // 自定义配置 Map 对象
//                .customMap(Collections.singletonMap("test", "baomidou"))
                // 自定义配置模板文件
//                .customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))
                .build();
    }

    /**
     * 策略配置
     */
    public static StrategyConfig strategyConfig() {
        return new StrategyConfig.Builder()
                // 开启大写命名
                .enableCapitalMode()
                // 开启跳过视图
                .enableSkipView()
                // 禁用 sql 过滤
                .disableSqlFilter()
                // 启用 schema
                .enableSchema()
                // 模糊表匹配(sql 过滤) likeTable 与 notLikeTable 只能配置一项
                .likeTable(new LikeTable("USER"))
                // 模糊表排除(sql 过滤) likeTable 与 notLikeTable 只能配置一项
//                .notLikeTable(new LikeTable("USER"))
                // 增加表匹配(内存过滤) include 与 exclude 只能配置一项
                .addInclude("t_simple")
                // 增加表排除匹配(内存过滤) include 与 exclude 只能配置一项
                //.addExclude("t_simple")
                // 增加过滤表前缀
                .addTablePrefix("t_", "c_")
                // 增加过滤表后缀
//                .addTableSuffix("string")
                // 增加过滤字段前缀
                .addFieldSuffix("_flag")
                // 增加过滤字段后缀
//                .addFieldSuffix("string")

                // controller 策略配置
//                .controllerBuilder()
                // 设置父类
//                .superClass(BaseController.class)
                // 开启驼峰转连字符
//                .enableHyphenStyle()
                // 	开启生成@RestController 控制器
//                .enableRestStyle()
                // 转换文件名称
//                .convertFileName()
                // 格式化文件名称
//                .formatFileName("%sAction")

                // 	service 策略配置
//                .serviceBuilder()
                // 设置 service 接口父类
//                .superServiceClass(BaseService.class)
                // 设置 service 实现类父类
//                .superServiceImplClass(BaseServiceImpl.class)
                // 格式化 service 接口文件名称
//                .formatServiceFileName("%sService")
                // 格式化 service 实现类文件名称
//                .formatServiceImplFileName("%sServiceImp")

                // mapper 策略配置
//                .mapperBuilder()
                // 设置父类
//                .superClass(BaseMapper.class)
                // 开启 @Mapper 注解
//                .enableMapperAnnotation()
                // 启用 BaseResultMap 生成
//                .enableBaseResultMap()
                // 启用 BaseColumnList
//                .enableBaseColumnList()
                // 设置缓存实现类
//                .cache(MyMapperCache.class)
                // 	格式化 mapper 文件名称
//                .formatMapperFileName("%sDao")
                // 格式化 xml 实现类文件名称
//                .formatXmlFileName("%sXml")

                // 实体策略配置
//                .entityBuilder()
                // 	设置父类
//                .superClass(BaseEntity.class)
                // 禁用生成 serialVersionUID
//                .disableSerialVersionUID()
                // 开启链式模型
//                .enableChainModel()
                // 	开启 lombok 模型
//                .enableLombok()
                // 	开启 Boolean 类型字段移除 is 前缀
//                .enableRemoveIsPrefix()
                // 开启生成实体时生成字段注解
//                .enableTableFieldAnnotation()
                // 开启 ActiveRecord 模型
//                .enableActiveRecord()
                // 乐观锁字段名(数据库)
//                .versionColumnName("version")
                // 乐观锁属性名(实体)
//                .versionPropertyName("version")
                // 逻辑删除字段名(数据库)
//                .logicDeleteColumnName("deleted")
                // 逻辑删除属性名(实体)
//                .logicDeletePropertyName("deleteFlag")
                // 数据库表映射到实体的命名策略
//                .naming(NamingStrategy.no_change)
                // 数据库表字段映射到实体的命名策略
//                .columnNaming(NamingStrategy.underline_to_camel)
                // 添加父类公共字段
//                .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
                // 添加忽略字段
//                .addIgnoreColumns("age")
                // 添加表字段填充
//                .addTableFills(new Column("create_time", FieldFill.INSERT))
                // 添加表字段填充
//                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                // 全局主键类型
//                .idType(IdType.AUTO)
                // 转换文件名称
//                .formatFileName("%sEntity")
                .build();
    }
}
