public static String buildSqlList(final String columnName, final Collection<? extends Object> paramsIn, final List<Object> paramsOut)
	{
		Check.assumeNotEmpty(paramsIn, "paramsIn not empty");

		final boolean embedSqlParams = paramsOut == null;
		
		final InArrayQueryFilter<?> builder = new InArrayQueryFilter<>(columnName, paramsIn)
				.setEmbedSqlParams(embedSqlParams);
		final String sql = builder.getSql();
		
		if(!embedSqlParams)
		{
			final List<Object> sqlParams = builder.getSqlParams(Env.getCtx());
			paramsOut.addAll(sqlParams);
		}
		
		return sql;
	}