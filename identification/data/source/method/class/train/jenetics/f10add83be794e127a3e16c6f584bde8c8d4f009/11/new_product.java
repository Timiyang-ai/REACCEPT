public TreeNode<T> insert(final int index, final TreeNode<T> child) {
		requireNonNull(child);
		if (isAncestor(child)) {
			throw new IllegalArgumentException("The new child is an ancestor.");
		}

		if (child._parent != null) {
			child._parent.remove(child);
		}

		child.setParent(this);
		createChildrenIfMissing();
		_children.add(index, child);

		TreeNode<T> parent = this;
		while (parent != null) {
			parent = parent._parent;
		}

		return this;
	}