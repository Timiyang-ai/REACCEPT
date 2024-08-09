public static int getSQLValue (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValue(trxName, sql, params.toArray(new Object[params.size()]));
    }