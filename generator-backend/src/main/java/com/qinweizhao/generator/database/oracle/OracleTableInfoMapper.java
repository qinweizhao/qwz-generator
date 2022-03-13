package com.qinweizhao.generator.database.oracle;

import com.baomidou.mybatisplus.annotation.DbType;
import com.qinweizhao.generator.database.TableInfoMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hccake
 */
@Mapper
public interface OracleTableInfoMapper extends TableInfoMapper {

    @Override
    default DbType dbType() {
        return DbType.ORACLE;
    }

}
