public TreeNode<V> expand(final Map<Var<V>, Tree<V, ?>> vars) {
		return expand(_pattern, vars);
	}