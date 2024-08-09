	@Test(invocationCount = 5)
	public void toTree() {
		final TreeNode<Op<Double>> tree = Program.of(
			6,
			OPERATIONS,
			TERMINALS
		);

		final ISeq<FlatTreeNode<Op<Double>>> seq = FlatTreeNode.of(tree).flattenedNodes();

		Assert.assertEquals(
			Program.toTree(seq, TERMINALS),
			tree
		);
	}