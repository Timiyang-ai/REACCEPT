public void remove(final int index) {
		_children.remove(index).setParent(null);
	}