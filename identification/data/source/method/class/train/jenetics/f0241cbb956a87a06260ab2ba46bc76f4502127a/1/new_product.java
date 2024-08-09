public <B> TreeNode<B> map(final Function<? super T, ? extends B> mapper) {
		final TreeNode<B> target = TreeNode.of(mapper.apply(getValue()));
		fill(this, target, mapper);
		return target;
	}