package com.qinweizhao.generator.service;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.qinweizhao.generator.database.TableInfoMapper;
import com.qinweizhao.generator.datasource.WrappedDataSource;
import com.qinweizhao.generator.model.bo.ColumnInfo;
import com.qinweizhao.generator.model.bo.TableDetails;
import com.qinweizhao.generator.model.bo.TableInfo;
import com.qinweizhao.generator.model.domain.PageParam;
import com.qinweizhao.generator.model.domain.PageResult;
import com.qinweizhao.generator.model.qo.TableInfoQO;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 表信息
 *
 * @author hccake
 * @date 2020-06-17 10:24:47
 */
@RequiredArgsConstructor
@DS("#header.dsName")
public class TableInfoQuery {

    private final Map<DbType, TableInfoMapper> tableInfoMapperMap;

    /**
     * 动态路由数据连接
     */
    @Resource(type = DataSource.class)
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    /**
     * 根据QueryObject查询分页数据
     *
     * @param pageParam 分页参数
     * @param qo        查询参数对象
     * @return 分页数据
     */
    public PageResult<TableInfo> queryPage(PageParam pageParam, TableInfoQO qo) {
        return getTableInfoMapper().queryPage(pageParam, qo);
    }

    /**
     * 根据表名查询对应表信息
     *
     * @param tableName 表名
     * @return TableInfo
     */
    public TableInfo queryTableInfo(String tableName) {
        return getTableInfoMapper().queryTableInfo(tableName);
    }

    /**
     * 查询指定表的列信息
     *
     * @param tableName 表名
     * @return List<ColumnInfo>
     */
    public List<ColumnInfo> listColumnInfo(String tableName) {
        return getTableInfoMapper().listColumnInfo(tableName);
    }

    /**
     * 查询指定表的详细信息
     *
     * @param tableName 表名称
     * @return TableDetails
     */
    public TableDetails queryTableDetails(String tableName) {
        DbType dbType = getDbType();
        TableInfoMapper baseMapper = tableInfoMapperMap.get(dbType);
        // 查询表信息
        TableInfo tableInfo = baseMapper.queryTableInfo(tableName);
        // 查询列信息
        List<ColumnInfo> columnInfoList = baseMapper.listColumnInfo(tableName);

        TableDetails tableDetails = new TableDetails();
        tableDetails.setDbType(dbType);
        tableDetails.setTableName(tableInfo.getTableName());
        tableDetails.setEngine(tableInfo.getEngine());
        tableDetails.setTableComment(tableInfo.getTableComment());
        tableDetails.setCreateTime(tableInfo.getCreateTime());
        tableDetails.setColumnInfos(columnInfoList);

        return tableDetails;
    }

    /**
     * 获取当前连接所使用的数据库类型
     *
     * @return DbType
     */
    private DbType getDbType() {
        String dsName = DynamicDataSourceContextHolder.peek();
        WrappedDataSource dataSource = (WrappedDataSource) dynamicRoutingDataSource.getDataSource(dsName);
        return dataSource.getDbType();
    }

    /**
     * 获取当期数据库类型对应的 mapper
     *
     * @return TableInfoMapper
     */
    private TableInfoMapper getTableInfoMapper() {
        DbType dbType = getDbType();
        return tableInfoMapperMap.get(dbType);
    }

}
