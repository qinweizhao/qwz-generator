package com.qinweizhao.generator.controller;


import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.SelectData;
import com.qinweizhao.generator.model.entity.TemplateGroup;
import com.qinweizhao.generator.model.qo.TemplateGroupQO;
import com.qinweizhao.generator.model.result.BaseResultCode;
import com.qinweizhao.generator.model.result.R;
import com.qinweizhao.generator.model.vo.TemplateGroupPageVO;
import com.qinweizhao.generator.service.TemplateGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模板组
 *
 * @author hccake
 * @date 2020-06-19 19:11:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gen/template/group")
@Tag(name = "模板组管理")
public class TemplateGroupController {

    private final TemplateGroupService templateGroupService;

    /**
     * 分页查询
     *
     * @param pageParam       分页参数
     * @param templateGroupQO 模板组查询对象
     * @return R
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    // @PreAuthorize("@per.hasPermission('codegen:templategroup:read')" )
    public R<PageResult<TemplateGroupPageVO>> getTemplateGroupPage(PageParam pageParam,
                                                                   TemplateGroupQO templateGroupQO) {
        return R.ok(templateGroupService.queryPage(pageParam, templateGroupQO));
    }

    /**
     * 通过id查询模板组
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询")
    @GetMapping("/{id}")
    // @PreAuthorize("@per.hasPermission('codegen:templategroup:read')" )
    public R<TemplateGroup> getById(@PathVariable("id") Integer id) {
        return R.ok(templateGroupService.getById(id));
    }

    /**
     * 新增模板组
     *
     * @param templateGroup 模板组
     * @return R
     */
    @Operation(summary = "新增模板组")
    // @CreateOperationLogging(msg = "新增模板组" )
    @PostMapping
    // @PreAuthorize("@per.hasPermission('codegen:templategroup:add')" )
    public R<Void> save(@RequestBody TemplateGroup templateGroup) {
        return templateGroupService.save(templateGroup) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增模板组失败");
    }

    /**
     * 复制模板组
     *
     * @param resourceId    原模板组id
     * @param templateGroup 新模板组实体
     * @return R
     */
    @PostMapping("/{resourceId}")
    @Operation(summary = "复制模板组")
    public R<Void> copy(@PathVariable Integer resourceId, @RequestBody TemplateGroup templateGroup) {
        return templateGroupService.copy(resourceId, templateGroup) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "复制模板组失败");
    }

    /**
     * 修改模板组
     *
     * @param templateGroup 模板组
     * @return R
     */
    @Operation(summary = "修改模板组")
    // @UpdateOperationLogging(msg = "修改模板组" )
    @PutMapping
    // @PreAuthorize("@per.hasPermission('codegen:templategroup:edit')" )
    public R<Void> updateById(@RequestBody TemplateGroup templateGroup) {
        return templateGroupService.updateById(templateGroup) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改模板组失败");
    }

    /**
     * 通过id删除模板组
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除模板组")
    // @DeleteOperationLogging(msg = "通过id删除模板组" )
    @DeleteMapping("/{id}")
    // @PreAuthorize("@per.hasPermission('codegen:templategroup:del')" )
    public R<Void> removeById(@PathVariable Integer id) {
        return templateGroupService.removeGroupById(id) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除模板组失败");
    }

    /**
     * 获取模板组选择框数据
     *
     * @return R
     */
    @Operation(summary = "获取模板组选择框")
    // @DeleteOperationLogging(msg = "通过id删除模板组" )
    @GetMapping("/select")
    // @PreAuthorize("@per.hasPermission('codegen:templategroup:del')" )
    public R<List<SelectData<Void>>> listSelectData() {
        return R.ok(templateGroupService.listSelectData());
    }

}