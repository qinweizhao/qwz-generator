package com.qinweizhao.generator.database.postgre;

import com.baomidou.mybatisplus.annotation.DbType;
import com.qinweizhao.generator.database.TableInfoMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hccake
 */
@Mapper
public interface PostgreTableInfoMapper extends TableInfoMapper {

    @Override
    default DbType dbType() {
        return DbType.POSTGRE_SQL;
    }

}
