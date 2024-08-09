	@Test
	public void map() {
		final TreeNode<Integer> tree = TreeNode.of(0)
			.attach(TreeNode.of(1)
				.attach(TreeNode.of(3))
				.attach(TreeNode.of(4)))
			.attach(TreeNode.of(2)
				.attach(TreeNode.of(5))
				.attach(TreeNode.of(6)));

		final TreeNode<String> mapped = tree.map(Objects::toString);

		Assert.assertEquals(
			mapped.stream()
				.map(TreeNode::getValue)
				.toArray(String[]::new),
			tree.stream()
				.map(TreeNode::getValue)
				.map(Objects::toString)
				.toArray(String[]::new)
		);
	}