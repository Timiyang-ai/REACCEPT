public static Timestamp getSQLValueTS (String trxName, String sql, List<Object> params)
    {
		Object[] arr = new Object[params.size()];
		params.toArray(arr);
		return getSQLValueTS(trxName, sql, arr);
    }