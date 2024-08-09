public static String getSQLValueStringEx (String trxName, String sql, List<Object> params)
    {
		return getSQLValueStringEx(trxName, sql, params.toArray(new Object[params.size()]));
    }