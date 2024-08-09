	@Test
	public void offsets() {
		final TreeNode<Op<Double>> tree = Program.of(
			6,
			OPERATIONS,
			TERMINALS
		);
		final ISeq<FlatTreeNode<Op<Double>>> seq = FlatTreeNode.of(tree).flattenedNodes();

		final int[] expected = seq.stream()
			.mapToInt(FlatTreeNode::childOffset)
			.toArray();

		final int[] offsets = Program.offsets(seq);
		Assert.assertEquals(offsets, expected);
	}