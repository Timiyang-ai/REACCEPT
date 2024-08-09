public static String getSQLValueString (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValueString(trxName, sql, params.toArray(new Object[params.size()]));
    }