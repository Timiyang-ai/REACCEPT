TreeNode<String> expand(final Map<String, Tree<String, ?>> variables) {
		return expand(variables, Function.identity());
	}