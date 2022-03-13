package com.qinweizhao.generator.util;

import lombok.Data;

import java.util.List;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/6/21 17:08
 */
@Data
public class SimpleTreeNode<T> implements TreeNode<T> {
	private T id;
	private T parentId;
	private List<? extends TreeNode<T>> children;

	public SimpleTreeNode() {
	}

	@Override
	public T getId() {
		return this.id;
	}

	@Override
	public T getParentId() {
		return this.parentId;
	}

	@Override
	public List<? extends TreeNode<T>> getChildren() {
		return this.children;
	}

	public void setId(T id) {
		this.id = id;
	}

	public void setParentId(T parentId) {
		this.parentId = parentId;
	}

	@Override
	public void setChildren(List<? extends TreeNode<T>> children) {
		this.children = children;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof SimpleTreeNode)) {
			return false;
		} else {
			SimpleTreeNode<?> other = (SimpleTreeNode)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label47: {
					Object this$id = this.getId();
					Object other$id = other.getId();
					if (this$id == null) {
						if (other$id == null) {
							break label47;
						}
					} else if (this$id.equals(other$id)) {
						break label47;
					}

					return false;
				}

				Object this$parentId = this.getParentId();
				Object other$parentId = other.getParentId();
				if (this$parentId == null) {
					if (other$parentId != null) {
						return false;
					}
				} else if (!this$parentId.equals(other$parentId)) {
					return false;
				}

				Object this$children = this.getChildren();
				Object other$children = other.getChildren();
				if (this$children == null) {
					if (other$children != null) {
						return false;
					}
				} else if (!this$children.equals(other$children)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof SimpleTreeNode;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "SimpleTreeNode(id=" + this.getId() + ", parentId=" + this.getParentId() + ", children=" + this.getChildren() + ")";
	}
}
