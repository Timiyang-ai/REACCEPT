public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional, Object ... params)
	{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
        if (optional)
        {
            list.add (new KeyNamePair(-1, ""));
        }
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, sql, getSQLException(e));
        }
        finally
        {
            close(rs, pstmt);
            rs= null;
            pstmt = null;
        }
        KeyNamePair[] retValue = new KeyNamePair[list.size()];
        list.toArray(retValue);
    //  s_log.fine("getKeyNamePairs #" + retValue.length);
        return retValue;
	}