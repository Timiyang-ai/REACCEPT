public TreeNode<T> remove(final int index) {
		if (_children == null) {
			throw new ArrayIndexOutOfBoundsException(format(
				"Child index is out of bounds: %s", index
			));
		}

		final TreeNode<T> child = _children.remove(index);
		assert child._parent == this;

		TreeNode<T> parent = this;
		while (parent != null) {
			parent = parent._parent;
		}

		child.setParent(null);

		return this;
	}