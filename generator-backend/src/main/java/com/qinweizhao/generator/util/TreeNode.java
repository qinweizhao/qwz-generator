package com.qinweizhao.generator.util;

import java.util.List;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/6/21 17:05
 */
public interface TreeNode<T> {
	T getId();

	T getParentId();

	void setChildren(List<? extends TreeNode<T>> var1);

	List<? extends TreeNode<T>> getChildren();
}
