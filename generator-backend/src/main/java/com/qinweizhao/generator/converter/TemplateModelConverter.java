package com.qinweizhao.generator.converter;

import com.qinweizhao.generator.model.dto.TemplateEntryCreateDTO;
import com.qinweizhao.generator.model.dto.TemplateEntryUpdateDTO;
import com.qinweizhao.generator.model.entity.TemplateEntry;
import com.qinweizhao.generator.model.entity.TemplateGroup;
import com.qinweizhao.generator.model.vo.TemplateEntryTree;
import com.qinweizhao.generator.model.vo.TemplateEntryVO;
import com.qinweizhao.generator.model.vo.TemplateGroupPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/6/19 20:01
 */
@Mapper
public interface TemplateModelConverter {

    TemplateModelConverter INSTANCE = Mappers.getMapper(TemplateModelConverter.class);

    /**
     * 模板组 PO 转换为 PageVO
     *
     * @param templateGroup 模板组实体
     * @return TemplateGroupPageVO 模板组分页VO
     */
    TemplateGroupPageVO groupPoToPageVo(TemplateGroup templateGroup);

    /**
     * 转换 TemplateEntry 为 TemplateEntryVO
     *
     * @param templateEntry templateEntry
     * @return VO
     */
    TemplateEntryVO entryPoToVo(TemplateEntry templateEntry);

    /**
     * 转换 TemplateEntryCreateDTO to TemplateEntry
     *
     * @param entryCreateDTO entryCreateDTO
     * @return TemplateDirectoryEntry 持久对象
     */
    TemplateEntry entryCreateDtoToPo(TemplateEntryCreateDTO entryCreateDTO);

    /**
     * 转换 TemplateEntryUpdateDTO to TemplateEntry
     *
     * @param entryUpdateDTO entryUpdateDTO
     * @return TemplateEntry 持久对象
     */
    TemplateEntry entryUpdateDtoToPo(TemplateEntryUpdateDTO entryUpdateDTO);

    /**
     * 转换为目录树
     *
     * @param templateEntry templateDirectoryEntry
     * @return TemplateDirectoryTree
     */
    TemplateEntryTree entryPoToTree(TemplateEntry templateEntry);

}
