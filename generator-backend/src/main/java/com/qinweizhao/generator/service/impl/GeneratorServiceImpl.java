package com.qinweizhao.generator.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.qinweizhao.generator.constant.TemplateEntryTypeEnum;
import com.qinweizhao.generator.engine.TemplateEngineDelegator;
import com.qinweizhao.generator.engine.TemplateEngineTypeEnum;
import com.qinweizhao.generator.model.bo.FileEntry;
import com.qinweizhao.generator.model.bo.TableDetails;
import com.qinweizhao.generator.model.bo.TemplateFile;
import com.qinweizhao.generator.model.dto.GeneratorOptionDTO;
import com.qinweizhao.generator.model.entity.TemplateEntry;
import com.qinweizhao.generator.service.GeneratorService;
import com.qinweizhao.generator.service.TableInfoQuery;
import com.qinweizhao.generator.service.TemplateEntryService;
import com.qinweizhao.generator.util.GenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Hccake
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

    private final TableInfoQuery tableInfoQuery;

    private final TemplateEntryService templateEntryService;

    private final TemplateEngineDelegator templateEngineDelegator;

    /**
     * 生成代码
     *
     * @param generatorOptionDTO 代码生成的一些配置信息
     * @return 已生成的代码数据
     */
    @Override
    public byte[] generatorCode(GeneratorOptionDTO generatorOptionDTO) throws IOException {
        // 获取生成后的文件项 map
        Map<String, FileEntry> map = getStringFileEntryMap(generatorOptionDTO);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ZipOutputStream zip = new ZipOutputStream(outputStream)) {
            // 循环写入数据
            for (Map.Entry<String, FileEntry> entry : map.entrySet()) {
                FileEntry fileEntry = entry.getValue();
                // 只处理文件
                if (TemplateEntryTypeEnum.FILE.getType().equals(fileEntry.getType())) {
                    // 添加到zip
                    String filePath = entry.getKey();
                    zip.putNextEntry(new ZipEntry(filePath));
                    IoUtil.write(zip, StandardCharsets.UTF_8, false, fileEntry.getContent());
                    zip.closeEntry();
                }
            }
            // 手动结束 zip，防止文件末端未被写入
            zip.finish();
            return outputStream.toByteArray();
        }
    }

    @Override
    public List<FileEntry> previewCode(GeneratorOptionDTO generateOptionDTO) {
        // 获取生成后的文件项 map
        Map<String, FileEntry> map = getStringFileEntryMap(generateOptionDTO);
        // 忽略大小写的排序
        return CollectionUtil.sort(map.values(),
                Comparator.comparing(FileEntry::getFilename, String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * 获得生成后的 代码地址：代码文件 的 map
     *
     * @param generateOptionDTO 生成参数
     * @return Map<String, FileEntry>
     */
    private Map<String, FileEntry> getStringFileEntryMap(GeneratorOptionDTO generateOptionDTO) {
        // 获取模板文件信息
        List<TemplateEntry> templateEntryList = templateEntryService.listByIds(generateOptionDTO.getTemplateEntryIds());
        List<TemplateFile> templateFiles = templateEntryService.convertToTemplateFile(templateEntryList);

        return getStringFileEntryMap(generateOptionDTO, templateFiles);
    }

    /**
     * 获得生成后的 代码地址：代码文件 的 map
     *
     * @param generateOptionDTO 生成参数
     * @param templateFiles     模板文件
     * @return Map<String, FileEntry>
     */
    private Map<String, FileEntry> getStringFileEntryMap(GeneratorOptionDTO generateOptionDTO,
                                                         List<TemplateFile> templateFiles) {
        Map<String, FileEntry> map = new HashMap<>(templateFiles.size());

        for (String tableName : generateOptionDTO.getTableNames()) {
            // 查询表详情
            TableDetails tableDetails = tableInfoQuery.queryTableDetails(tableName);
            // 生成代码
            Map<String, FileEntry> fileEntryMap = generatorCode(tableDetails, generateOptionDTO.getTablePrefix(),
                    generateOptionDTO.getGenProperties(), templateFiles);
            map.putAll(fileEntryMap);
        }
        return map;
    }

    /**
     * 代码生成
     *
     * @return Map<String, FileEntry>
     */
    public Map<String, FileEntry> generatorCode(TableDetails tableDetails, String tablePrefix,
                                                Map<String, String> customProperties, List<TemplateFile> templateFiles) {

        Map<String, FileEntry> map = new HashMap<>(templateFiles.size());

        // 模板渲染
        Map<String, Object> context = GenUtils.getContext(tableDetails, tablePrefix, customProperties);

        for (TemplateFile templateFile : templateFiles) {
            FileEntry fileEntry = new FileEntry();
            fileEntry.setType(templateFile.getType());

            // 替换路径中的占位符
            String filename = StrUtil.format(templateFile.getFilename(), context);
            fileEntry.setFilename(filename);

            String parentFilePath = GenUtils.evaluateRealPath(templateFile.getParentFilePath(), context);
            fileEntry.setParentFilePath(parentFilePath);

            // 如果是文件
            if (TemplateEntryTypeEnum.FILE.getType().equals(fileEntry.getType())) {
                fileEntry.setFilePath(GenUtils.concatFilePath(parentFilePath, filename));
                // 文件内容渲染
                TemplateEngineTypeEnum engineTypeEnum = TemplateEngineTypeEnum.of(templateFile.getEngineType());
                String content = templateEngineDelegator.render(engineTypeEnum, templateFile.getContent(), context);
                fileEntry.setContent(content);
            } else {
                String currentPath = GenUtils.evaluateRealPath(templateFile.getFilename(), context);
                fileEntry.setFilePath(GenUtils.concatFilePath(parentFilePath, currentPath));
            }

            map.put(fileEntry.getFilePath(), fileEntry);
        }

        return map;
    }

}
