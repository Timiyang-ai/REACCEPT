<V> TreeNode<V> expand(
		final Map<String, Tree<V, ?>> variables,
		final Function<? super String, ? extends V> mapper
	) {
		return expand(_pattern, variables, mapper);
	}