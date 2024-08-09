public Stream<TreeMatchResult<V>> results() {
		return _tree.stream()
			.flatMap(tree -> _pattern.match(tree).stream());
	}