package com.qinweizhao.generator.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 模板文件目录项 查询对象
 *
 * @author hccake
 * @date 2020-06-19 19:11:41
 */
@Data
@Schema(title = "模板文件目录项查询对象")
public class TemplateEntryQO {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @Schema(title = "")
    private Integer id;

}