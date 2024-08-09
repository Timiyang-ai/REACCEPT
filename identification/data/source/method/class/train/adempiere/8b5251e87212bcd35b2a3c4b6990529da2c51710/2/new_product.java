public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional, List<Object> params)
	{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
        if (optional)
        {
            list.add (KeyNamePair.EMPTY);
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
        catch (SQLException e)
        {
			throw new DBException(e, sql, params); // metas: tsa
        }
        finally
        {
            close(rs, pstmt);
            rs = null; pstmt = null;
        }
		return list.toArray(new KeyNamePair[list.size()]);
	}