public static String getSQLValueString (String trxName, String sql, Object... params)
    {
    	String retValue = null;
    	try
    	{
    		retValue = getSQLValueStringEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return retValue;
    }