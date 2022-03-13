package com.qinweizhao.generator.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 数据源
 *
 * @author hccake
 * @date 2020-06-17 10:24:47
 */
@Data
@Schema(title = "数据源配置信息")
public class DataSourceConfigDTO {

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
     * 密码（未加密）
     */
    @Schema(title = "密码")
    private String pass;

    /**
     * 数据源连接
     */
    @Schema(title = "数据源连接")
    private String url;

}
