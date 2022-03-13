package com.qinweizhao.generator.controller;

import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.SelectData;
import com.qinweizhao.generator.model.dto.DataSourceConfigDTO;
import com.qinweizhao.generator.model.entity.DataSourceConfig;
import com.qinweizhao.generator.model.qo.DataSourceConfigQO;
import com.qinweizhao.generator.model.result.BaseResultCode;
import com.qinweizhao.generator.model.result.R;
import com.qinweizhao.generator.model.vo.DataSourceConfigPageVO;
import com.qinweizhao.generator.service.DataSourceConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据源
 *
 * @author hccake
 * @date 2020-06-17 10:24:47
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gen/datasource-config")
@Tag(name = "数据源管理")
public class DataSourceConfigController {

    private final DataSourceConfigService dataSourceConfigService;

    /**
     * 分页查询
     *
     * @param pageParam          分页对象
     * @param dataSourceConfigQO 数据源
     * @return R
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    // @PreAuthorize("@per.hasPermission('gen:datasourceconfig:read')" )
    public R<PageResult<DataSourceConfigPageVO>> getDataSourceConfigPage(PageParam pageParam,
                                                                         DataSourceConfigQO dataSourceConfigQO) {
        return R.ok(dataSourceConfigService.queryPage(pageParam, dataSourceConfigQO));
    }

    /**
     * 通过id查询数据源
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询")
    @GetMapping("/{id}")
    // @PreAuthorize("@per.hasPermission('gen:datasourceconfig:read')" )
    public R<DataSourceConfig> getById(@PathVariable("id") Integer id) {
        return R.ok(dataSourceConfigService.getById(id));
    }

    /**
     * 新增数据源
     *
     * @param dataSourceConfigDTO 数据源
     * @return R
     */
    @Operation(summary = "新增数据源")
    // @CreateOperationLogging(msg = "新增数据源" )
    @PostMapping
    // @PreAuthorize("@per.hasPermission('gen:datasourcecofig:add')" )
    public R<Void> save(@RequestBody DataSourceConfigDTO dataSourceConfigDTO) {
        return dataSourceConfigService.save(dataSourceConfigDTO) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增数据源失败");
    }

    /**
     * 修改数据源
     *
     * @param dataSourceConfigDTO 数据源
     * @return R
     */
    @Operation(summary = "修改数据源")
    // @UpdateOperationLogging(msg = "修改数据源" )
    @PutMapping
    // @PreAuthorize("@per.hasPermission('gen:datasourceconfig:edit')" )
    public R<Void> updateById(@RequestBody DataSourceConfigDTO dataSourceConfigDTO) {
        return dataSourceConfigService.update(dataSourceConfigDTO) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改数据源失败");
    }

    /**
     * 通过id删除数据源
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除数据源")
    // @DeleteOperationLogging(msg = "通过id删除数据源" )
    @DeleteMapping("/{id}")
    // @PreAuthorize("@per.hasPermission('gen:datasourceconfig:del')" )
    public R<Void> removeById(@PathVariable Integer id) {
        return dataSourceConfigService.removeById(id) ? R.ok()
                : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除数据源失败");
    }

    /**
     * 获取selectData数据
     *
     * @return R
     */
    @Operation(summary = "获取selectData数据")
    @GetMapping("/select")
    public R<List<SelectData<Void>>> listSelectData() {
        List<SelectData<Void>> selectDataList = dataSourceConfigService.listSelectData();
        return R.ok(selectDataList);
    }

}
