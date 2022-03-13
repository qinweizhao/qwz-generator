package com.qinweizhao.generator;

import cn.hutool.core.lang.ClassScanner;
import com.qinweizhao.generator.database.DbTypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.SpringDocConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/9/12 16:21
 */
@Slf4j
@SpringBootApplication(exclude = SpringDocConfiguration.class)
public class GeneratorApplication {

    public static void main(String[] args) throws ClassNotFoundException {
        loadTypeConverter();
        SpringApplication.run(GeneratorApplication.class);
    }

    /**
     * 加载所有的类型转换器
     */
    private static void loadTypeConverter() throws ClassNotFoundException {
        // 包扫描，以便注册所有的 TypeConverter
        Set<Class<?>> classes = ClassScanner.scanPackage("com.qinweizhao.generator.database",
                DbTypeConverter.class::isAssignableFrom);
        for (Class<?> aClass : classes) {
            Class.forName(aClass.getName());
            log.info("TypeConverter 加载成功：" + aClass);
        }
    }

}
