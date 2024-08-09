public Stream<TreeMatchResult<V>> results() {
		return _tree.stream()
			.flatMap((Tree<V, ?> tree) -> _pattern.match(tree)
				.map(Stream::of)
				.orElseGet(Stream::empty));
	}