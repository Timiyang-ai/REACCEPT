Stream<TreeMatchResult<V>> results(final BiPredicate<V, String> equals) {
		@SuppressWarnings("unchecked")
		final Stream<Tree<V, ?>> ts = (Stream<Tree<V, ?>>)_tree.stream();

		return ts
			.flatMap(tree -> _pattern.match(tree, equals)
				.map(Stream::of)
				.orElseGet(Stream::empty));
	}