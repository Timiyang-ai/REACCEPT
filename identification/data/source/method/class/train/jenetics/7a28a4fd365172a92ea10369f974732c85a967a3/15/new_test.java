	@Test(dataProvider = "patterns")
	public void expand(
		final String pattern,
		final String[] trees,
		final String expanded
	) {
		final TreePattern<String> tp = TreePattern.compile(pattern);
		final Map<Var<String>, Tree<String, ?>> vars = IntStream.range(0, trees.length)
			.mapToObj(i -> new Object() {
					final Var<String> name = Var.of(VARS[i]);
					final Tree<String, ?> tree = TreeNode.parse(trees[i]);
				})
			.collect(Collectors.toMap(o -> o.name, o -> o.tree));

		Assert.assertEquals(tp.expand(vars).toParenthesesString(), expanded);
	}