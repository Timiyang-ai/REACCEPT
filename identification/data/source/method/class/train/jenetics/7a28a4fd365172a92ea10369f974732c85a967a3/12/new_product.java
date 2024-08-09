<V> TreeNode<V> expand(
		final Map<String, Tree<V, ?>> variables,
		final Function<? super String, ? extends V> mapper
	) {
		return expand2(_pattern, variables, mapper);
	}