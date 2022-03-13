package com.qinweizhao.generator.service.impl;

import cn.hutool.core.lang.Assert;
import com.qinweizhao.generator.model.domain.PageResult;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.qinweizhao.generator.mapper.TemplateGroupMapper;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.SelectData;
import com.qinweizhao.generator.model.entity.TemplateGroup;
import com.qinweizhao.generator.model.qo.TemplateGroupQO;
import com.qinweizhao.generator.model.vo.TemplateGroupPageVO;
import com.qinweizhao.generator.service.TemplateEntryService;
import com.qinweizhao.generator.service.TemplateGroupService;
import com.qinweizhao.generator.service.TemplatePropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 模板组
 *
 * @author hccake
 * @date 2020-06-19 19:11:41
 */
@Service
@RequiredArgsConstructor
public class TemplateGroupServiceImpl extends ExtendServiceImpl<TemplateGroupMapper, TemplateGroup>
        implements TemplateGroupService {

    private final TemplateEntryService templateEntryService;

    private final TemplatePropertyService templatePropertyService;

    /**
     * 根据QueryObject查询分页数据
     *
     * @param pageParam 分页参数
     * @param qo        查询参数对象
     * @return 分页数据
     */
    @Override
    public PageResult<TemplateGroupPageVO> queryPage(PageParam pageParam, TemplateGroupQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    /**
     * 获取SelectData数据
     *
     * @return List<SelectData < ?>>
     */
    @Override
    public List<SelectData<Void>> listSelectData() {
        return baseMapper.listSelectData();
    }

    /**
     * 复制模板组
     *
     * @param resourceGroupId 原资源组id
     * @param templateGroup   模板组
     * @return boolean 复制成功: true
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean copy(Integer resourceGroupId, TemplateGroup templateGroup) {
        // 清空id
        templateGroup.setId(null);
        int insertFlag = baseMapper.insert(templateGroup);
        Assert.isTrue(SqlHelper.retBool(insertFlag), "复制模板组时，保存模板组失败：[{}]", templateGroup);
        // 获取落库成功后的自增ID
        Integer groupId = templateGroup.getId();
        // 复制模板目录文件
        templateEntryService.copy(resourceGroupId, groupId);
        // 复制模板属性配置
        templatePropertyService.copy(resourceGroupId, groupId);

        return true;
    }

    /**
     * 删除模板组
     *
     * @param groupId 模板组ID
     * @return 删除成功与否
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGroupById(Integer groupId) {
        // 删除模板组
        baseMapper.deleteById(groupId);
        // 删除关联文件
        templateEntryService.removeByGroupId(groupId);
        // 删除模板属性
        templatePropertyService.removeByGroupId(groupId);
        return true;
    }

}