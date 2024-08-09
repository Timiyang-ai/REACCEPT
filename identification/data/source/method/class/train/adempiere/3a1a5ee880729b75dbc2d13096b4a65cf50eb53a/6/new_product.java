public static int getSQLValue (String trxName, String sql, Object... params)
    {
    	int retValue = -1;
    	try
    	{
    		retValue = getSQLValueEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return retValue;
    }