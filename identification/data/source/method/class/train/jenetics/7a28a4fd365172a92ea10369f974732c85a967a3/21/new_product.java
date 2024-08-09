public <V> TreeNode<V>
	expand(final Map<Var<V>, Tree<? extends V, ?>> variables) {
		return expand((Tree<Node<V>, ?>)_pattern, variables);
	}