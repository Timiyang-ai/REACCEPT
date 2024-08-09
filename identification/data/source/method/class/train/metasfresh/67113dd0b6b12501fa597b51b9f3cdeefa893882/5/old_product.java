public static String buildSqlList(final Collection<? extends Object> paramsIn, final List<Object> paramsOut)
	{
		Check.assumeNotNull(paramsOut, "paramsOut not null");

		if (paramsIn == null || paramsIn.isEmpty())
		{
			return SQL_EmptyList;
		}

		final StringBuilder sql = new StringBuilder("?");
		final int len = paramsIn.size();
		for (int i = 1; i < len; i++)
		{
			sql.append(",?");
		}

		paramsOut.addAll(paramsIn);

		return sql.insert(0, "(").append(")").toString();
	}