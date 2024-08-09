TreeNode<String> expand(final Map<String, Tree<String, ?>> variables) {
		final TreeNode<String> root = TreeNode.of();
		expand(_pattern, variables, root, Objects::toString);
		return root;
	}