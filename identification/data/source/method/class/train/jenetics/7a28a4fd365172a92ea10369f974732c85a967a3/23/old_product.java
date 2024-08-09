public TreeNode<V> expand(final Map<Var<V>, Tree<V, ?>> variables) {
		return expand(_pattern, variables);
	}