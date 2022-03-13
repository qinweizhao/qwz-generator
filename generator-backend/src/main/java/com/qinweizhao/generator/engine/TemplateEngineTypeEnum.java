package com.qinweizhao.generator.engine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板引擎类型
 *
 * @author hccake
 */
@ToString
@Getter
@RequiredArgsConstructor
public enum TemplateEngineTypeEnum {

    // velocity 模板引擎
    VELOCITY(1),

    // Freemaker 模板引擎
    FREEMARKER(2);

    private static final Map<Integer, TemplateEngineTypeEnum> MAP = new HashMap<>();

    static {
        for (TemplateEngineTypeEnum engineTypeEnum : values()) {
            MAP.put(engineTypeEnum.getType(), engineTypeEnum);
        }
    }

    private final Integer type;

    public static TemplateEngineTypeEnum of(Integer type) {
        return MAP.get(type);
    }

}
