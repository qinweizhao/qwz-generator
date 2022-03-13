package com.qinweizhao.generator.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qinweizhao.generator.converter.TemplateModelConverter;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.domain.SelectData;
import com.qinweizhao.generator.model.entity.TemplateGroup;
import com.qinweizhao.generator.model.qo.TemplateGroupQO;
import com.qinweizhao.generator.model.vo.TemplateGroupPageVO;
import com.qinweizhao.generator.util.LambdaQueryWrapperX;
import com.qinweizhao.generator.util.WrappersX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 模板组
 *
 * @author hccake
 * @date 2020-06-19 19:11:41
 */
@Mapper
public interface TemplateGroupMapper extends ExtendMapper<TemplateGroup> {

    /**
     * 分页查询
     *
     * @param pageParam 分页参数
     * @param qo        查询条件
     * @return PageResult<TemplateGroupVO> 分页数据
     */
    default PageResult<TemplateGroupPageVO> queryPage(PageParam pageParam, TemplateGroupQO qo) {
        IPage<TemplateGroup> page = this.prodPage(pageParam);
        LambdaQueryWrapperX<TemplateGroup> wrapperX = WrappersX.lambdaQueryX(TemplateGroup.class)
                .likeIfPresent(TemplateGroup::getName, qo.getName());
        this.selectPage(page, wrapperX);
        IPage<TemplateGroupPageVO> voPage = page.convert(TemplateModelConverter.INSTANCE::groupPoToPageVo);
        return new PageResult<>(voPage.getRecords(), voPage.getTotal());
    }

    /**
     * 获取SelectData数据
     *
     * @return List<SelectData < Void>>
     */
    List<SelectData<Void>> listSelectData();

}