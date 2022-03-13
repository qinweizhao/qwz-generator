package com.qinweizhao.generator.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TreeUtils {
	public static <T extends TreeNode<I>, I> List<T> buildTree(List<T> nodes, I rootId) {
		return buildTree(nodes, rootId, Function.identity(), (Comparator)null);
	}

	public static <T extends TreeNode<I>, I> List<T> buildTree(List<T> nodes, I rootId, Comparator<? super T> comparator) {
		return buildTree(nodes, rootId, Function.identity(), comparator);
	}

	public static <T extends TreeNode<I>, I, R> List<T> buildTree(List<R> list, I rootId, Function<R, T> convertToTree) {
		return buildTree(list, rootId, convertToTree, (Comparator)null);
	}

	public static <T extends TreeNode<I>, I, R> List<T> buildTree(List<R> list, I rootId, Function<R, T> convertToTree, Comparator<? super T> comparator) {
		if (list != null && !list.isEmpty()) {
			Stream<T> tStream = list.stream().map(convertToTree);
			if (comparator != null) {
				tStream = tStream.sorted(comparator);
			}

			Map<I, List<T>> childrenMap = (Map)tStream.collect(Collectors.groupingBy(TreeNode::getParentId, LinkedHashMap::new, Collectors.toList()));
			List<T> treeList = (List)childrenMap.get(rootId);
			Assert.notEmpty(treeList, "错误的数据，找不到根节点的子节点", new Object[0]);
			treeList.forEach((node) -> {
				setChildren(node, childrenMap);
			});
			return treeList;
		} else {
			return new ArrayList();
		}
	}

	public static <T extends TreeNode<I>, I> void setChildren(T parent, Map<I, List<T>> childrenMap) {
		I parentId = parent.getId();
		List<T> children = (List)childrenMap.get(parentId);
		if (CollectionUtil.isNotEmpty(children)) {
			parent.setChildren(children);
			children.forEach((node) -> {
				setChildren(node, childrenMap);
			});
		} else {
			parent.setChildren(new ArrayList());
		}

	}

	public static <T extends TreeNode<?>> List<T> getLeafs(T parent) {
		List<T> leafs = new ArrayList();
		fillLeaf(parent, leafs);
		return leafs;
	}

	public static <T extends TreeNode> void fillLeaf(T parent, List<T> leafs) {
		List<T> children = parent.getChildren();
		if (CollectionUtil.isEmpty(children)) {
			leafs.add(parent);
		} else {
			Iterator var3 = children.iterator();

			while(var3.hasNext()) {
				T child = (T) var3.next();
				fillLeaf(child, leafs);
			}

		}
	}

	public static <T extends TreeNode<I>, I> List<I> getTreeNodeIds(List<T> treeList) {
		List<I> ids = new ArrayList();
		fillTreeNodeIds(ids, treeList);
		return ids;
	}

	public static <T extends TreeNode<I>, I> void fillTreeNodeIds(List<I> ids, List<T> treeList) {
		if (!CollectionUtil.isEmpty(treeList)) {
			Iterator var2 = treeList.iterator();

			while(var2.hasNext()) {
				T treeNode = (T) var2.next();
				ids.add(treeNode.getId());
				List<? extends TreeNode<I>> children = treeNode.getChildren();
				if (CollectionUtil.isNotEmpty(children)) {
					fillTreeNodeIds(ids, children);
				}
			}

		}
	}

	public static <T extends TreeNode<?>> List<T> treeToList(T treeNode) {
		return treeToList(treeNode, Function.identity());
	}

	public static <T extends TreeNode<?>, R> List<R> treeToList(T treeNode, Function<T, R> converter) {
		List<R> list = new ArrayList();
		Queue<T> queue = new LinkedList();
		queue.add(treeNode);

		while(!queue.isEmpty()) {
			TreeNode node = (TreeNode)queue.poll();
			if (node != null) {
				List<? extends TreeNode<?>> children = node.getChildren();
				if (CollectionUtil.isNotEmpty(children)) {
					queue.addAll((Collection<? extends T>) children);
				}

				node.setChildren((List)null);
				list.add(converter.apply((T) node));
			}
		}

		return list;
	}

	public static <T extends TreeNode<?>> List<T> treeToList(List<T> treeNodes) {
		return treeToList(treeNodes, Function.identity());
	}

	public static <T extends TreeNode<?>, R> List<R> treeToList(List<T> treeNodes, Function<T, R> converter) {
		return (List)treeNodes.stream().map((node) -> {
			return treeToList(node, converter);
		}).flatMap(Collection::stream).collect(Collectors.toList());
	}

	private TreeUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
}