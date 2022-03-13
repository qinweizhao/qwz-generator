package com.qinweizhao.generator.controller;

import com.qinweizhao.generator.converter.TemplateModelConverter;
import com.qinweizhao.generator.model.dto.TemplateEntryCreateDTO;
import com.qinweizhao.generator.model.dto.TemplateEntryUpdateDTO;
import com.qinweizhao.generator.model.entity.TemplateEntry;
import com.qinweizhao.generator.model.result.BaseResultCode;
import com.qinweizhao.generator.model.result.R;
import com.qinweizhao.generator.model.vo.TemplateEntryVO;
import com.qinweizhao.generator.service.TemplateEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 模板文件目录项
 *
 * @author hccake
 * @date 2020-06-19 19:11:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gen/template-entry")
@Tag(name = "模板文件目录项管理")
public class TemplateEntryController {

    private final TemplateEntryService templateEntryService;

    /**
     * 模板组的文件目录
     *
     * @param templateGroupId 模板组ID
     * @return R
     */
    @Operation(summary = "指定模板组的文件目录项")
    @GetMapping("/list/{templateGroupId}")
    // @PreAuthorize("@per.hasPermission('codegen:templatedirectoryentry:read')" )
    public R<List<TemplateEntryVO>> getTemplateDirectoryEntryPage(@PathVariable Integer templateGroupId) {
        List<TemplateEntry> entries = templateEntryService.listByTemplateGroupId(templateGroupId);
        List<TemplateEntryVO> vos = entries.stream().map(TemplateModelConverter.INSTANCE::entryPoToVo)
                .collect(Collectors.toList());
        return R.ok(vos);
    }

    /**
     * 移动目录项
     *
     * @param entryId        被移动的目录项ID
     * @param horizontalMove 是否移动到目标目录平级，否则移动到其内部
     * @param targetEntryId  目标目录项ID
     * @return R
     */
    @Operation(summary = "移动目录项")
    @PatchMapping("/{entryId}/position")
    public R<Void> move(@PathVariable Integer entryId, @RequestParam boolean horizontalMove,
                        @RequestParam Integer targetEntryId) {
        return templateEntryService.move(horizontalMove, entryId, targetEntryId) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "移动目录项失败");
    }

    /**
     * 新增模板目录项
     *
     * @param templateEntryCreateDTO 模板目录项
     * @return R
     */
    @Operation(summary = "新增模板目录项")
    // @CreateOperationLogging(msg = "新增模板文件目录项" )
    @PostMapping
    // @PreAuthorize("@per.hasPermission('codegen:templatedirectoryentry:add')" )
    public R<Integer> save(@RequestBody TemplateEntryCreateDTO templateEntryCreateDTO) {
        Integer entryId = templateEntryService.createEntry(templateEntryCreateDTO);
        return entryId != null ? R.ok(entryId) : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增模板目录项失败");
    }

    /**
     * 修改目录项
     *
     * @param templateEntryUpdateDTO 模板目录项
     * @return R
     */
    @Operation(summary = "修改目录项")
    @PutMapping
    public R<Void> updateEntry(@RequestBody TemplateEntryUpdateDTO templateEntryUpdateDTO) {
        return templateEntryService.updateEntry(templateEntryUpdateDTO) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改目录项失败");
    }

    /**
     * 通过id删除模板文件目录项
     *
     * @param id   id
     * @param mode 删除模式， 1：只删除本身，将子节点上移 2. 删除自身及其所有子节点
     * @return R
     */
    @Operation(summary = "通过id删除模板文件目录项")
    // @DeleteOperationLogging(msg = "通过id删除模板文件目录项" )
    @DeleteMapping("/{id}")
    // @PreAuthorize("@per.hasPermission('codegen:templatedirectoryentry:del')" )
    public R<Void> removeById(@PathVariable Integer id, @RequestParam Integer mode) {
        return templateEntryService.removeEntry(id, mode) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除模板文件目录项失败");
    }

    /**
     * 修改模板目录项内容
     *
     * @param id      模板项id
     * @param content 模板内容
     * @return R
     */
    @Operation(summary = "修改模板目录项内容")
    @PatchMapping("/content")
    public R<Void> updateContent(@RequestParam("id") Integer id, @RequestParam("content") String content) {
        return templateEntryService.updateContent(id, content) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改模板目录项内容失败");
    }

}