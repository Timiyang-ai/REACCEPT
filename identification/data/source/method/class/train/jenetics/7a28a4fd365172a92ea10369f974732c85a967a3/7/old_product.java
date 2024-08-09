private static <V> TreeNode<V> expand(
		final Tree<Node<V>, ?> template,
		final Map<Var<V>, Tree<V, ?>> vars
	) {
		final Map<Path, Var<V>> paths = template.stream()
			.filter((Tree<Node<V>, ?> n) -> n.getValue() instanceof Var)
			.collect(toMap(t -> t.childPath(), t -> (Var<V>)t.getValue()));

		//final Function<Var, String> m = d -> d.value;
		final TreeNode<V> tree = TreeNode.ofTree(
			template,
			n -> ((Val<V>)n).value()
		);

		for (Map.Entry<Path, Var<V>> var : paths.entrySet()) {
			final Path path = var.getKey();
			final Var<V> decl = var.getValue();
			final TreeNode<V> child = tree.childAtPath(path)
				.orElseThrow(AssertionError::new);

			final Tree<? extends V, ?> replacement = vars.get(decl);
			if (replacement != null) {
				tree.replaceAtPath(path, TreeNode.ofTree(replacement));
			} else {
				tree.removeAtPath(path);
			}
		}

		return tree;
	}