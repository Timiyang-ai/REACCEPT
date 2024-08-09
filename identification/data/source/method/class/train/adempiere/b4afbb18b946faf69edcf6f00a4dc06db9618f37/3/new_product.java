public static String getSQLValueString (String trxName, String sql, List<Object> params)
    {
		return getSQLValueString(trxName, sql, params.toArray(new Object[params.size()]));
    }