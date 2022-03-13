package com.qinweizhao.generator.database.sqlserver;

import com.baomidou.mybatisplus.annotation.DbType;
import com.qinweizhao.generator.database.TableInfoMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hccake
 */
@Mapper
public interface SqlServerTableInfoMapper extends TableInfoMapper {

    @Override
    default DbType dbType() {
        return DbType.SQL_SERVER;
    }

}
