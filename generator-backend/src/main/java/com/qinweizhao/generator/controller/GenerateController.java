package com.qinweizhao.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.bo.FileEntry;
import com.qinweizhao.generator.model.bo.TableInfo;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.dto.GeneratorOptionDTO;
import com.qinweizhao.generator.model.qo.TableInfoQO;
import com.qinweizhao.generator.model.result.R;
import com.qinweizhao.generator.service.GeneratorService;
import com.qinweizhao.generator.service.TableInfoQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代码生成器
 *
 * @author hccake
 * @date 2018-07-30
 */
@CrossOrigin
@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "代码生成")
public class GenerateController {

    private final GeneratorService generatorService;

    private final TableInfoQuery tableInfoQuery;

    /**
     * 表信息分页查询
     *
     * @param pageParam   分页参数
     * @param tableInfoQO 表信息查询对象
     * @return R
     */
    @Operation(summary = "表信息分页查询")
    @GetMapping("/table-info/page")
    public R<PageResult<TableInfo>> getDataSourceConfigPage(PageParam pageParam, TableInfoQO tableInfoQO) {
        return R.ok(tableInfoQuery.queryPage(pageParam, tableInfoQO));
    }

    /**
     * 生成代码
     */
    @Operation(summary = "生成代码")
    @PostMapping("/generate")
    public void generateCode(@RequestBody GeneratorOptionDTO generatorOptionDTO, HttpServletResponse response)
            throws IOException {
        byte[] data = generatorService.generatorCode(generatorOptionDTO);
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ballcat.zip\"");
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }

    /**
     * 生成预览代码
     *
     * @param preGenerateOptionDTO 预览
     * @return R<List < TemplateDirectory>>
     */
    @Operation(summary = "生成预览代码")
    @PostMapping("/preview")
    public R<List<FileEntry>> previewCode(@RequestBody GeneratorOptionDTO preGenerateOptionDTO) {
        return R.ok(generatorService.previewCode(preGenerateOptionDTO));
    }

}
