public Stream<TreeMatchResult<V>> results() {
		return _tree.stream()
			.flatMap((Tree<V, ?> tree) -> _pattern.match(tree, _equals)
				.map(Stream::of)
				.orElseGet(Stream::empty));
	}