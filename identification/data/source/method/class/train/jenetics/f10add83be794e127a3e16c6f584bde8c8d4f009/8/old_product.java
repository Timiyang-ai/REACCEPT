public TreeNode<T> remove(final int index) {
		_children.remove(index).setParent(null);
		return this;
	}