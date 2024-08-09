public static BigDecimal getSQLValueBDEx (String trxName, String sql, List<Object> params) throws DBException
    {
		return getSQLValueBDEx(trxName, sql, params.toArray(new Object[params.size()]));
    }