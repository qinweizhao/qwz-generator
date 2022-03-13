package com.qinweizhao.generator.config;

import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.processor.DsHeaderProcessor;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import com.qinweizhao.generator.datasource.DsRequestProcessor;
import com.qinweizhao.generator.datasource.MasterDataSourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/6/15 17:37 动态数据源加载
 */
@Configuration
@RequiredArgsConstructor
public class DynamicDataSourceConfiguration {

    /**
     * JDBC 动态数据源提供者
     *
     * @param dataSourceProperties spring数据源配置
     * @return MasterDataSourceProvider 动态数据源提供者
     */
    @Bean
    public MasterDataSourceProvider masterDataSourceProvider(DataSourceProperties dataSourceProperties,
                                                             DefaultDataSourceCreator defaultDataSourceCreator) {
        return new MasterDataSourceProvider(dataSourceProperties, defaultDataSourceCreator);
    }

    @Bean
    public DsProcessor dsProcessor() {
        DsRequestProcessor requestProcessor = new DsRequestProcessor();
        DsHeaderProcessor headerProcessor = new DsHeaderProcessor();
        DsSessionProcessor sessionProcessor = new DsSessionProcessor();
        DsSpelExpressionProcessor spelExpressionProcessor = new DsSpelExpressionProcessor();
        requestProcessor.setNextProcessor(headerProcessor);
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(spelExpressionProcessor);
        return requestProcessor;
    }

}
