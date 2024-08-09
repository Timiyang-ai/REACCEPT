	@Test
	public void remove() {
		final TreeNode<Integer> tree = newTree(5, new Random(123));
		final DefaultMutableTreeNode stree = newSwingTree(5, new Random(123));

		tree.remove(0);
		stree.remove(0);
		Assert.assertTrue(equals(tree, stree));
	}