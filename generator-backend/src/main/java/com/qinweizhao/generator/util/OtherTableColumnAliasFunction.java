package com.qinweizhao.generator.util;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * 连表查询时，从其他表获取的查询条件
 *
 * @author hccake
 */
@FunctionalInterface
public interface OtherTableColumnAliasFunction<T> extends SFunction<T, String> {

}
