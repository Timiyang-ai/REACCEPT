public Stream<TreeMatchResult<V>> results(final BiPredicate<V, String> equals) {
		return _tree.stream()
			.flatMap((Tree<V, ?> tree) -> _pattern.match(tree, equals)
				.map(Stream::of)
				.orElseGet(Stream::empty));
	}