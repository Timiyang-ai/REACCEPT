Stream<Tree<V, ?>> results(final BiPredicate<V, String> equals) {
		@SuppressWarnings("unchecked")
		final Stream<Tree<V, ?>> ts = (Stream<Tree<V, ?>>)_tree.stream();
		return  ts.filter(n -> _pattern.matches(n, equals));
	}