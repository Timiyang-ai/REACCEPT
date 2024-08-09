public default Path childPath() {
		final Iterator<T> it = pathFromAncestorIterator(getRoot());
		final int[] path = new int[level()];

		T tree = null;
		int index = 0;
		while (it.hasNext()) {
			final T child = it.next();
			if (tree != null) {
				path[index++] = tree.getIndex(child);
			}

			tree = child;
		}

		assert index == path.length;

		return new Path(path);
	}