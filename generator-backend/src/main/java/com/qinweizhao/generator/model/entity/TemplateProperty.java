package com.qinweizhao.generator.model.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("gen_template_property")
@Schema(title = "模板属性配置")
public class TemplateProperty {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
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
     * 默认值(可为空值)
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
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
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Schema(title = "逻辑删除")
    private Long deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "修改时间")
    private LocalDateTime updateTime;

}
