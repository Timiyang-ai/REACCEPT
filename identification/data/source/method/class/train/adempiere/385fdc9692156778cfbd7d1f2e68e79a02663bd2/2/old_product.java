public static BigDecimal getSQLValueBDEx (String trxName, String sql, Object... params) throws DBException
    {
    	BigDecimal retValue = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try
    	{
    		pstmt = prepareStatement(sql, trxName);
    		setParameters(pstmt, params);
    		rs = pstmt.executeQuery();
    		if (rs.next())
    			retValue = rs.getBigDecimal(1);
    		else
    			log.fine("No Value " + sql);
    	}
    	catch (SQLException e)
    	{
    		//log.log(Level.SEVERE, sql, getSQLException(e));
    		throw new DBException(e, sql);
    	}
    	finally
    	{
    		close(rs, pstmt);
    		rs = null; pstmt = null;
    	}
    	return retValue;
    }