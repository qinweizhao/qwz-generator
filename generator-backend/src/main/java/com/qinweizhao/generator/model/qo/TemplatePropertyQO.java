package com.qinweizhao.generator.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 模板属性配置 查询对象
 *
 * @author hccake
 * @date 2020-06-22 15:46:39
 */
@Data
@Schema(title = "模板属性配置查询对象")
public class TemplatePropertyQO {

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

}