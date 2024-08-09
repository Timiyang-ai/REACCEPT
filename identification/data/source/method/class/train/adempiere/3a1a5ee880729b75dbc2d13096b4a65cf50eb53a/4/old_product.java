public static int getSQLValue (String trxName, String sql, Collection<Object> params)
    {
		Object[] arr = new Object[params.size()];
		params.toArray(arr);
		return getSQLValue(trxName, sql, arr);
    }