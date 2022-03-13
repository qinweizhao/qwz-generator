package com.qinweizhao.generator.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据源
 *
 * @author hccake
 * @date 2020-06-17 10:24:47
 */
@Data
@TableName("gen_data_source_config")
@Schema(title = "数据源")
public class DataSourceConfig {

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
     * 用户名
     */
    @Schema(title = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(title = "密码")
    private String password;

    /**
     * 数据源连接
     */
    @Schema(title = "数据源连接")
    private String url;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    /**
     * 删除时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "删除时间")
    private LocalDateTime updateTime;

}
