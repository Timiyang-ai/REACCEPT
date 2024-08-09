	@Test(dataProvider = "matchResults")
	public void results(
		final String patternString,
		final String treeString,
		final String[] results
	) {
		final TreePattern<String> pattern = TreePattern.compile(patternString);
		final Tree<String, ?> tree = TreeNode.parse(treeString);
		final String[] matches = pattern.matcher(tree).results()
			.map(t -> t.tree().toParenthesesString())
			.toArray(String[]::new);

		Assert.assertEquals(matches, results);
	}