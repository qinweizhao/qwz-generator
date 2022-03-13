package com.qinweizhao.generator.converter;


import com.qinweizhao.generator.model.dto.DataSourceConfigDTO;
import com.qinweizhao.generator.model.entity.DataSourceConfig;
import com.qinweizhao.generator.model.vo.DataSourceConfigPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/6/18 9:49
 */
@Mapper
public interface DataSourceConfigConverter {

    DataSourceConfigConverter INSTANCE = Mappers.getMapper(DataSourceConfigConverter.class);

    /**
     * PO 转 PageVO
     *
     * @param dataSourceConfig 数据源配置
     * @return DataSourceConfigPageVO 数据源配置分页VO
     */
    DataSourceConfigPageVO poToPageVo(DataSourceConfig dataSourceConfig);

    /**
     * 转换Dto为Po
     *
     * @param dataSourceConfigDTO DataSourceConfig
     * @return dtoToPo
     */
    DataSourceConfig dtoToPo(DataSourceConfigDTO dataSourceConfigDTO);

}
