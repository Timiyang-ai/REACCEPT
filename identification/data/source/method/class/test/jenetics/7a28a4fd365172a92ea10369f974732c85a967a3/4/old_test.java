	@Test(dataProvider = "patterns")
	public void matches(
		final String patternString,
		final String treeString,
		final boolean matches
	) {
		final TreePattern<String> pattern = TreePattern.compile(patternString);
		final Tree<String, ?> tree = TreeNode.parse(treeString);
		final TreeMatcher<String> matcher = pattern.matcher(tree);

		Assert.assertEquals(
			matcher.matches(),
			matches,
			format("%s -> %s: %s", patternString, treeString, matches)
		);
	}