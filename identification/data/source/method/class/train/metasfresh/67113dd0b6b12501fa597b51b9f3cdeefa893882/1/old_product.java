@Deprecated
	public static String buildSqlList(final Collection<? extends Object> paramsIn)
	{
		if (paramsIn == null || paramsIn.isEmpty())
		{
			return SQL_EmptyList;
		}

		final StringBuilder sql = new StringBuilder();
		for (final Object paramIn : paramsIn)
		{
			if (sql.length() > 0)
			{
				sql.append(",");
			}

			if (paramIn == null)
			{
				sql.append("NULL");
			}
			else if (paramIn instanceof BigDecimal)
			{
				sql.append(TO_NUMBER((BigDecimal)paramIn, DisplayType.Number));
			}
			else if (paramIn instanceof Integer)
			{
				sql.append(paramIn);
			}
			else if (paramIn instanceof Date)
			{
				sql.append(TO_DATE(TimeUtil.asTimestamp((Date)paramIn)));
			}
			else
			{
				sql.append(TO_STRING(paramIn.toString()));
			}
		}

		return sql.insert(0, "(").append(")").toString();
	}