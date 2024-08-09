public static BigDecimal getSQLValueBD (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValueBD(trxName, sql, params.toArray(new Object[params.size()]));
    }