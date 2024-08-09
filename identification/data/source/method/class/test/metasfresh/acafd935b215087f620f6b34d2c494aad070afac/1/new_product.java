public IQueryFilter<T> createFilter(@NonNull final List<AttributesKeyPattern> attributesKeyPatterns)
	{
		Check.assumeNotEmpty(attributesKeyPatterns, "attributesKeyPatterns is not empty");

		if (attributesKeyPatterns.contains(AttributesKeyPattern.ALL))
		{
			return ConstantQueryFilter.of(true);
		}

		if (attributesKeyPatterns.contains(AttributesKeyPattern.OTHER))
		{
			return ConstantQueryFilter.of(true);
		}

		final ArrayList<IQueryFilter<T>> filters = new ArrayList<>();

		for (final AttributesKeyPattern attributesKeyPattern : attributesKeyPatterns)
		{
			final String likeExpression = attributesKeyPattern.getSqlLikeString();
			final boolean ignoreCase = false;
			final StringLikeFilter<T> filter = new StringLikeFilter<>(modelColumn.getColumnName(), likeExpression, ignoreCase);

			filters.add(filter);
		}

		if (filters.isEmpty())
		{
			return ConstantQueryFilter.of(true);
		}
		else if (filters.size() == 1)
		{
			return filters.get(0);
		}
		else
		{
			return queryBL.createCompositeQueryFilter(clazz)
					.setJoinOr()
					.addFilters(filters);
		}
	}