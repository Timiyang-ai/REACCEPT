public static int getSQLValueEx (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValueEx(trxName, sql, params.toArray(new Object[params.size()]));
    }