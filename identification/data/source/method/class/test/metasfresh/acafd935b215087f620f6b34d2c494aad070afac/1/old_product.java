public IQueryFilter<T> createFilter(@NonNull final List<AttributesKey> attributesKeys)
	{
		final ICompositeQueryFilter<T> result = queryBL.createCompositeQueryFilter(clazz).setJoinOr();

		for (final AttributesKey attributesKey : attributesKeys)
		{
			final IQueryFilter<T> filter = createFilter(attributesKey, attributesKeys);
			if (filter != null)
			{
				result.addFilter(filter);
			}
		}

		return result;
	}