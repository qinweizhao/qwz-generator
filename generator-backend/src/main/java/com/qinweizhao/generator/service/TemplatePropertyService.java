package com.qinweizhao.generator.service;

import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.entity.TemplateProperty;
import com.qinweizhao.generator.model.qo.TemplatePropertyQO;
import com.qinweizhao.generator.model.vo.TemplatePropertyPageVO;

import java.util.List;

/**
 * 模板属性配置
 *
 * @author hccake
 * @date 2020-06-22 15:46:39
 */
public interface TemplatePropertyService extends ExtendService<TemplateProperty> {

    /**
     * 根据QueryObject查询分页数据
     *
     * @param pageParam 分页参数
     * @param qo        查询参数对象
     * @return 分页数据
     */
    PageResult<TemplatePropertyPageVO> queryPage(PageParam pageParam, TemplatePropertyQO qo);

    /**
     * 获取模板组的所有配置
     *
     * @param templateGroupId 模板组ID
     * @return List<TemplatePropertyVO> 配置列表
     */
    List<TemplateProperty> listByTemplateGroupId(Integer templateGroupId);

    /**
     * 复制模板属性配置
     *
     * @param resourceId 原模板组ID
     * @param groupId    模板模板组ID
     */
    void copy(Integer resourceId, Integer groupId);

    /**
     * 根据模板组ID 删除模板属性
     *
     * @param groupId 模板组ID
     */
    void removeByGroupId(Integer groupId);

}