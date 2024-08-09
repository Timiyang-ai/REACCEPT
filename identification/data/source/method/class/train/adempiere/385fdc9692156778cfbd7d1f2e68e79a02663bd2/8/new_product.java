public static Timestamp getSQLValueTSEx (String trxName, String sql, Collection<Object> params) throws DBException
    {
		return getSQLValueTSEx(trxName, sql, params.toArray(new Object[params.size()]));
    }