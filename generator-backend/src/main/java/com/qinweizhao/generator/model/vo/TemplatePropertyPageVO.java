package com.qinweizhao.generator.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 模板属性配置
 *
 * @author hccake
 * @date 2020-06-22 15:46:39
 */
@Data
@Schema(title = "模板属性配置")
public class TemplatePropertyPageVO {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(title = "ID")
    private Integer id;

    /**
     * 模板组ID
     */
    @Schema(title = "模板组ID")
    private Integer groupId;

    /**
     * 标题
     */
    @Schema(title = "标题")
    private String title;

    /**
     * 属性键
     */
    @Schema(title = "属性键")
    private String propKey;

    /**
     * 默认值
     */
    @Schema(title = "默认值")
    private String defaultValue;

    /**
     * 必填，1：是，0：否
     */
    @Schema(title = "必填，1：是，0：否")
    private Integer required;

    /**
     * 备注信息
     */
    @Schema(title = "备注信息")
    private String remarks;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Schema(title = "修改时间")
    private LocalDateTime updateTime;

}