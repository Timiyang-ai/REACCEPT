public static BigDecimal getSQLValueBD (String trxName, String sql, Collection<Object> params)
    {
		Object[] arr = new Object[params.size()];
		params.toArray(arr);
		return getSQLValueBD(trxName, sql, arr);
    }