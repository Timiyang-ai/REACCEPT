public static int getSQLValueEx (String trxName, String sql, List<Object> params)
    {
		return getSQLValueEx(trxName, sql, params.toArray(new Object[params.size()]));
    }