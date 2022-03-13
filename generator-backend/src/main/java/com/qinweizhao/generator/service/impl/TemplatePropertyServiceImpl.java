package com.qinweizhao.generator.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.mapper.TemplatePropertyMapper;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.entity.TemplateProperty;
import com.qinweizhao.generator.model.qo.TemplatePropertyQO;
import com.qinweizhao.generator.model.vo.TemplatePropertyPageVO;
import com.qinweizhao.generator.service.TemplatePropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板属性配置
 *
 * @author hccake
 * @date 2020-06-22 15:46:39
 */
@Service
public class TemplatePropertyServiceImpl extends ExtendServiceImpl<TemplatePropertyMapper, TemplateProperty>
        implements TemplatePropertyService {

    /**
     * 根据QueryObject查询分页数据
     *
     * @param pageParam 分页参数
     * @param qo        查询参数对象
     * @return 分页数据
     */
    @Override
    public PageResult<TemplatePropertyPageVO> queryPage(PageParam pageParam, TemplatePropertyQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    /**
     * 根据模板组ID获取模板组的所有配置
     *
     * @param templateGroupId 模板组ID
     * @return List<TemplateProperty> 配置列表
     */
    @Override
    public List<TemplateProperty> listByTemplateGroupId(Integer templateGroupId) {
        return baseMapper.listByTemplateGroupId(templateGroupId);
    }

    /**
     * 复制模板属性配置
     *
     * @param resourceGroupId 原模板组ID
     * @param targetGroupId   模板模板组ID
     */
    @Override
    public void copy(Integer resourceGroupId, Integer targetGroupId) {
        List<TemplateProperty> templateProperties = baseMapper.listByTemplateGroupId(resourceGroupId);
        if (CollectionUtil.isNotEmpty(templateProperties)) {
            List<TemplateProperty> list = new ArrayList<>();
            for (TemplateProperty x : templateProperties) {
                x.setId(null);
                x.setCreateTime(null);
                x.setUpdateTime(null);
                x.setGroupId(targetGroupId);
                list.add(x);
            }
            baseMapper.insertBatchSomeColumn(list);
        }
    }

    /**
     * 根据模板组ID 删除模板属性
     *
     * @param groupId 模板组ID
     */
    @Override
    public void removeByGroupId(Integer groupId) {
        baseMapper.removeByGroupId(groupId);
    }

}
