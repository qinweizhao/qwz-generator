package com.qinweizhao.generator.controller;

import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.converter.TemplatePropertyConverter;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.entity.TemplateProperty;
import com.qinweizhao.generator.model.qo.TemplatePropertyQO;
import com.qinweizhao.generator.model.result.BaseResultCode;
import com.qinweizhao.generator.model.result.R;
import com.qinweizhao.generator.model.vo.TemplatePropertyPageVO;
import com.qinweizhao.generator.service.TemplatePropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 模板属性配置
 *
 * @author hccake
 * @date 2020-06-22 15:46:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gen/template/property")
@Tag(name = "模板属性配置管理")
public class TemplatePropertyController {

    private final TemplatePropertyService templatePropertyService;

    /**
     * 查询模板组对应的所有属性
     *
     * @param templateGroupId 模板组ID
     * @return R
     */
    @Operation(summary = "模板组属性")
    @GetMapping("/list/{groupId}")
    public R<List<TemplatePropertyPageVO>> getTemplatePropertyList(@PathVariable("groupId") Integer templateGroupId) {
        List<TemplateProperty> templateProperties = templatePropertyService.listByTemplateGroupId(templateGroupId);
        List<TemplatePropertyPageVO> vos = templateProperties.stream()
                .map(TemplatePropertyConverter.INSTANCE::poToPageVo).collect(Collectors.toList());
        return R.ok(vos);
    }

    /**
     * 分页查询
     *
     * @param pageParam          分页对象
     * @param templatePropertyQO 模板属性配置
     * @return R
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    // @PreAuthorize("@per.hasPermission('codegen:templateproperty:read')" )
    public R<PageResult<TemplatePropertyPageVO>> getTemplatePropertyPage(PageParam pageParam,
                                                                         TemplatePropertyQO templatePropertyQO) {
        return R.ok(templatePropertyService.queryPage(pageParam, templatePropertyQO));
    }

    /**
     * 通过id查询模板属性配置
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询")
    @GetMapping("/{id}")
    // @PreAuthorize("@per.hasPermission('codegen:templateproperty:read')" )
    public R<TemplateProperty> getById(@PathVariable("id") Integer id) {
        return R.ok(templatePropertyService.getById(id));
    }

    /**
     * 新增模板属性配置
     *
     * @param templateProperty 模板属性配置
     * @return R
     */
    @Operation(summary = "新增模板属性配置")
    // @CreateOperationLogging(msg = "新增模板属性配置" )
    @PostMapping
    // @PreAuthorize("@per.hasPermission('codegen:templateproperty:add')" )
    public R<Void> save(@RequestBody TemplateProperty templateProperty) {
        return templatePropertyService.save(templateProperty) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增模板属性配置失败");
    }

    /**
     * 修改模板属性配置
     *
     * @param templateProperty 模板属性配置
     * @return R
     */
    @Operation(summary = "修改模板属性配置")
    // @UpdateOperationLogging(msg = "修改模板属性配置" )
    @PutMapping
    // @PreAuthorize("@per.hasPermission('codegen:templateproperty:edit')" )
    public R<Void> updateById(@RequestBody TemplateProperty templateProperty) {
        return templatePropertyService.updateById(templateProperty) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改模板属性配置失败");
    }

    /**
     * 通过id删除模板属性配置
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除模板属性配置")
    // @DeleteOperationLogging(msg = "通过id删除模板属性配置" )
    @DeleteMapping("/{id}")
    // @PreAuthorize("@per.hasPermission('codegen:templateproperty:del')" )
    public R<Void> removeById(@PathVariable Integer id) {
        return templatePropertyService.removeById(id) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除模板属性配置失败");
    }

}