public static BigDecimal getSQLValueBDEx (String trxName, String sql, Collection<Object> params) throws DBException
    {
		return getSQLValueBDEx(trxName, sql, params.toArray(new Object[params.size()]));
    }