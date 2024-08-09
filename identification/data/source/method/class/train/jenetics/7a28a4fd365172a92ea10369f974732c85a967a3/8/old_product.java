Stream<Tree<V, ?>> results() {
		@SuppressWarnings("unchecked")
		final Stream<Tree<V, ?>> ts = (Stream<Tree<V, ?>>)_tree.stream();
		return  ts.filter(_pattern::matches);
	}