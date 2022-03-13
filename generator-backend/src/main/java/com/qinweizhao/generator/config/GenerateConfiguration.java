package com.qinweizhao.generator.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.qinweizhao.generator.database.TableInfoMapper;
import com.qinweizhao.generator.engine.TemplateEngine;
import com.qinweizhao.generator.engine.TemplateEngineDelegator;
import com.qinweizhao.generator.engine.TemplateEngineTypeEnum;
import com.qinweizhao.generator.service.TableInfoQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成的配置类
 *
 * @author hccake
 */
@Configuration(proxyBeanMethods = false)
public class GenerateConfiguration {

    /**
     * 模板引擎委托器
     *
     * @return TemplateEngineDelegator
     */
    @Bean
    public TemplateEngineDelegator templateEngineDelegator(List<TemplateEngine> templateEngineList) {
        Map<TemplateEngineTypeEnum, TemplateEngine> templateEngineMap = new EnumMap<>(TemplateEngineTypeEnum.class);
        for (TemplateEngine templateEngine : templateEngineList) {
            templateEngineMap.put(templateEngine.type(), templateEngine);
        }
        return new TemplateEngineDelegator(templateEngineMap);
    }

    /**
     * TableInfoQuery 查询器
     *
     * @return TableInfoQuery
     */
    @Bean
    @Primary
    public TableInfoQuery tableInfoQuery(List<TableInfoMapper> tableInfoMapperList) {
        Map<DbType, TableInfoMapper> tableInfoMapperEnumMap = new EnumMap<>(DbType.class);
        for (TableInfoMapper tableInfoMapper : tableInfoMapperList) {
            tableInfoMapperEnumMap.put(tableInfoMapper.dbType(), tableInfoMapper);
        }
        return new TableInfoQuery(tableInfoMapperEnumMap);
    }

}
