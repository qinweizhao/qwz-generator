package com.qinweizhao.generator.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 模板组
 *
 * @author hccake
 * @date 2020-06-19 19:11:41
 */
@Data
@TableName("gen_template_group")
@Schema(title = "模板组")
public class TemplateGroup {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @Schema(title = "ID")
    private Integer id;

    /**
     * 名称
     */
    @Schema(title = "名称")
    private String name;

    /**
     * 备注
     */
    @Schema(title = "备注")
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
