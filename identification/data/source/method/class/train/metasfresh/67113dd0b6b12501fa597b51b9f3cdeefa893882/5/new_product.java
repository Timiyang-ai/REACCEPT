public static String buildSqlList(final Collection<? extends Object> paramsIn, @NonNull final List<Object> paramsOut)
	{
		return buildSqlList(paramsIn, paramsOut::addAll);
	}