public static <A> TreeNode<Op<A>> toTree(
		final ISeq<? extends FlatTree<? extends Op<A>, ?>> nodes,
		final ISeq<? extends Op<A>> operations,
		final ISeq<? extends Op<A>> terminals
	) {
		if (nodes.isEmpty()) {
			throw new IllegalArgumentException("Tree nodes must not be empty.");
		}

		final Op<A> op = requireNonNull(nodes.get(0).getValue());
		final TreeNode<Op<A>> tree = TreeNode.of(op);
		return toTree(
			tree,
			0,
			nodes,
			operations,
			terminals,
			RandomRegistry.getRandom()
		);
	}