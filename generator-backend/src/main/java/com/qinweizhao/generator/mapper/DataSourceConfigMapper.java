package com.qinweizhao.generator.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qinweizhao.generator.converter.DataSourceConfigConverter;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.domain.SelectData;
import com.qinweizhao.generator.model.entity.DataSourceConfig;
import com.qinweizhao.generator.model.qo.DataSourceConfigQO;
import com.qinweizhao.generator.model.vo.DataSourceConfigPageVO;
import com.qinweizhao.generator.util.LambdaQueryWrapperX;
import com.qinweizhao.generator.util.WrappersX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据源
 *
 * @author hccake
 * @date 2020-06-17 10:24:47
 */
@Mapper
public interface DataSourceConfigMapper extends ExtendMapper<DataSourceConfig> {

    /**
     * 分页查询
     *
     * @param pageParam 分页参数
     * @param qo        查询条件
     * @return PageResult<DataSourceConfigVO> 分页结果数据
     */
    default PageResult<DataSourceConfigPageVO> queryPage(PageParam pageParam, DataSourceConfigQO qo) {
        IPage<DataSourceConfig> page = this.prodPage(pageParam);
        LambdaQueryWrapperX<DataSourceConfig> wrapperX = WrappersX.lambdaQueryX(DataSourceConfig.class)
                .likeIfPresent(DataSourceConfig::getName, qo.getName());
        this.selectPage(page, wrapperX);
        IPage<DataSourceConfigPageVO> voPage = page.convert(DataSourceConfigConverter.INSTANCE::poToPageVo);
        return new PageResult<>(voPage.getRecords(), voPage.getTotal());
    }

    /**
     * 获取SelectData集合
     *
     * @return List<SelectData < Void>>
     */
    List<SelectData<Void>> listSelectData();

}
