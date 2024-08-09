	private static IQueryFilter<DummyModel> createFilter(final AttributesKeyPattern... patterns)
	{
		final AttributesKeyQueryHelper<DummyModel> queryHelper = newQueryHelper();
		return queryHelper.createFilter(ImmutableList.copyOf(patterns));
	}