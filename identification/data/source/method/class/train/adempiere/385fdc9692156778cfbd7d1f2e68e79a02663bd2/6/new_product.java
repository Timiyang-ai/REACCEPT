public static Timestamp getSQLValueTSEx (String trxName, String sql, Object... params)
    {
    	Timestamp retValue = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try
    	{
    		pstmt = prepareStatement(sql, trxName);
    		setParameters(pstmt, params);
    		rs = pstmt.executeQuery();
    		if (rs.next())
    			retValue = rs.getTimestamp(1);
    		else
    			log.info("No Value " + sql);
    	}
    	catch (SQLException e)
    	{
			throw new DBException(e, sql, params); // metas: tsa
    	}
    	finally
    	{
    		close(rs, pstmt);
    		rs = null; pstmt = null;
    	}
    	return retValue;
    }