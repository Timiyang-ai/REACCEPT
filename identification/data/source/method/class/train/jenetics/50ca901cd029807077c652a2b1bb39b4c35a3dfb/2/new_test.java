	@Test(dataProvider = "tokens")
	public void tokenize(final String tree, final String[] tokens) {
		final List<Token> tokenize = TreeParser.tokenize(tree);
		Assert.assertEquals(
			tokenize.stream()
				.map(t -> t.seq)
				.toArray(String[]::new),
			tokens
		);
	}