public static String getSQLValueString (String trxName, String sql, Collection<Object> params)
    {
		Object[] arr = new Object[params.size()];
		params.toArray(arr);
		return getSQLValueString(trxName, sql, arr);
    }