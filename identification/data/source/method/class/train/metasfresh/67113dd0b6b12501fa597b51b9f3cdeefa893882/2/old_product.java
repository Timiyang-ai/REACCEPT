public static String buildSqlList(final String columnName, final Collection<? extends Object> paramsIn, final List<Object> paramsOut)
	{
		Check.assumeNotEmpty(paramsIn, "paramsIn not empty");
		Check.assumeNotNull(paramsOut, "paramsOut not null");

		final InArrayQueryFilter<?> builder = new InArrayQueryFilter<>(columnName, paramsIn);
		final String sql = builder.getSql();
		final List<Object> sqlParams = builder.getSqlParams(Env.getCtx());

		paramsOut.addAll(sqlParams);
		return sql;
	}