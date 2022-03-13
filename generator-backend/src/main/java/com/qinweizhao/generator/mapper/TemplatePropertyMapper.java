package com.qinweizhao.generator.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qinweizhao.generator.converter.TemplatePropertyConverter;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.entity.TemplateProperty;
import com.qinweizhao.generator.model.qo.TemplatePropertyQO;
import com.qinweizhao.generator.model.vo.TemplatePropertyPageVO;
import com.qinweizhao.generator.util.LambdaQueryWrapperX;
import com.qinweizhao.generator.util.WrappersX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 模板属性配置
 *
 * @author hccake
 * @date 2020-06-22 15:46:39
 */
@Mapper
public interface TemplatePropertyMapper extends ExtendMapper<TemplateProperty> {

    /**
     * 分页查询
     *
     * @param pageParam 分页参数
     * @param qo        查询参数
     * @return PageResult<TemplatePropertyVO> 分页数据
     */
    default PageResult<TemplatePropertyPageVO> queryPage(PageParam pageParam, TemplatePropertyQO qo) {
        IPage<TemplateProperty> page = this.prodPage(pageParam);
        LambdaQueryWrapperX<TemplateProperty> wrapperX = WrappersX.lambdaQueryX(TemplateProperty.class)
                .eqIfPresent(TemplateProperty::getId, qo.getId())
                .eqIfPresent(TemplateProperty::getGroupId, qo.getGroupId());
        this.selectPage(page, wrapperX);
        IPage<TemplatePropertyPageVO> voPage = page.convert(TemplatePropertyConverter.INSTANCE::poToPageVo);
        return new PageResult<>(voPage.getRecords(), voPage.getTotal());
    }

    /**
     * 根据模板组ID获取模板组的所有配置
     *
     * @param templateGroupId 模板组ID
     * @return List<TemplateProperty> 配置列表
     */
    default List<TemplateProperty> listByTemplateGroupId(Integer templateGroupId) {
        return this
                .selectList(Wrappers.<TemplateProperty>lambdaQuery().eq(TemplateProperty::getGroupId, templateGroupId));

    }

    /**
     * 根据模板组ID 删除模板属性
     *
     * @param groupId 模板组ID
     */
    default void removeByGroupId(Integer groupId) {
        this.delete(Wrappers.lambdaQuery(TemplateProperty.class).eq(TemplateProperty::getGroupId, groupId));
    }

}