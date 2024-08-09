<V> TreeNode<V> expand(
		final Map<String, Tree<V, ?>> variables,
		final Function<? super String, ? extends V> mapper
	) {
		final TreeNode<V> root = TreeNode.of();
		expand(_pattern, variables, root, mapper);
		return root;
	}