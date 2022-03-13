package com.qinweizhao.generator.model.bo;

import lombok.Data;

/**
 * 根据数据库中列获取到属性信息
 *
 * @author Hccake
 */
@Data
public class ColumnProperties {

    /**
     * 列表
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 备注
     */
    private String comments;

    /**
     * 属性名（小驼峰）
     */
    private String attrName;

    /**
     * 首字母大写的属性名
     */
    private String capitalizedAttrName;

    /**
     * 属性类型 - Java
     */
    private String attrType;

    /**
     * 属性类型 - ts
     */
    private String tsAttrType;

    /**
     * 其他信息
     */
    private String extra;

    /**
     * 字段类型
     */
    private String columnType;

}
