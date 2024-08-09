public TreeNode<T> remove(final int index) {
		if (_children == null) {
			throw new ArrayIndexOutOfBoundsException(format(
				"Child index is out of bounds: %s", index
			));
		}

		final TreeNode<T> child = _children.remove(index);
		assert child._parent == this;
		child.setParent(null);

		if (_children.isEmpty()) {
			_children = null;
		}

		return this;
	}