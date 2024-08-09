public static ValueNamePair[] getValueNamePairs(String sql, boolean optional, List<Object> params)
	{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ValueNamePair> list = new ArrayList<ValueNamePair>();
        if (optional)
        {
            list.add (ValueNamePair.EMPTY);
        }
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                list.add(new ValueNamePair(rs.getString(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            throw new DBException(e, sql);
        }
        finally
        {
            close(rs, pstmt);
            rs = null; pstmt = null;
        }
		return list.toArray(new ValueNamePair[list.size()]);
	}