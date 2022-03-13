package com.qinweizhao.generator.database;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qinweizhao.generator.model.bo.ColumnInfo;
import com.qinweizhao.generator.model.bo.TableInfo;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.qo.TableInfoQO;
import com.qinweizhao.generator.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码生成器
 *
 * @author Hccake
 */
public interface TableInfoMapper {

    /**
     * 获取当前对应的数据库类型
     *
     * @return DbType
     */
    DbType dbType();

    /**
     * 分页查询
     *
     * @param pageParam 分页参数
     * @param qo        查询数据
     * @return PageResult<TableInfo>
     */
    default PageResult<TableInfo> queryPage(PageParam pageParam, TableInfoQO qo) {
        IPage<TableInfo> page = PageUtil.prodPage(pageParam);
        this.selectByPage(page, qo.getTableName());
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    /**
     * 分页查询表格
     *
     * @param page      分页参数
     * @param tableName 表名
     * @return 填充后的分页数据
     */
    IPage<TableInfo> selectByPage(IPage<?> page, @Param("tableName") String tableName);

    /**
     * 根据表名查询对应表信息
     *
     * @param tableName 表名
     * @return TableInfo
     */
    TableInfo queryTableInfo(@Param("tableName") String tableName);

    /**
     * 查询列信息
     *
     * @param tableName 表名
     * @return List<ColumnInfo>
     */
    List<ColumnInfo> listColumnInfo(@Param("tableName") String tableName);

}
