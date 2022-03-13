package com.qinweizhao.generator.database.mysql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.qinweizhao.generator.database.TableInfoMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hccake
 */
@Mapper
public interface MysqlTableInfoMapper extends TableInfoMapper {

    @Override
    default DbType dbType() {
        return DbType.MYSQL;
    }

}
