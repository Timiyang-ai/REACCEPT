public TreeNode<T> remove(final int index) {
		final TreeNode<T> child = _children.remove(index);
		assert child._parent == this;

		TreeNode<T> parent = this;
		while (parent != null) {
			parent._size -= child.size();
			parent = parent._parent;
		}

		child.setParent(null);
		//_size -= child.size();

		return this;
	}