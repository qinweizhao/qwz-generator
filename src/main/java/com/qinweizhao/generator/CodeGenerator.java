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

}
