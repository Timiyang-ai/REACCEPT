public default int[] childPath() {
		final T root = getRoot();

		final Iterator<T> it = pathFromAncestorIterator(root);
		final List<Integer> path = new ArrayList<>();

		T tree = root;
		while (it.hasNext()) {
			final T child = it.next();
			path.add(tree.getIndex(child));

			tree = child;
		}

		return path.subList(1, path.size()).stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}