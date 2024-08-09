public static Timestamp getSQLValueTS (String trxName, String sql, Object... params)
    {
    	try
    	{
    		return getSQLValueTSEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return null;
    }