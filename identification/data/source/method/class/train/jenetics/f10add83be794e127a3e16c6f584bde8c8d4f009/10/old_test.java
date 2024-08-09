	@Test
	public void insert() {
		final Random random = new Random(123);

		final TreeNode<Integer> tree = newTree(5, random);
		final TreeNode<Integer> tree1 = newTree(2, random);

		random.setSeed(123);
		final DefaultMutableTreeNode stree = newSwingTree(5, random);
		final DefaultMutableTreeNode stree1 = newSwingTree(2, random);

		tree.childAt(1).insert(0, tree1);
		Assert.assertFalse(equals(tree, stree));

		((DefaultMutableTreeNode)stree.getChildAt(1)).insert(stree1, 0);
		Assert.assertTrue(equals(tree, stree));
	}