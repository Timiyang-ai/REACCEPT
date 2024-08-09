public static BigDecimal getSQLValueBD (String trxName, String sql, Object... params)
    {
    	try
    	{
    		return getSQLValueBDEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return null;
    }