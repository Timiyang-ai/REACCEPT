public long getLong(int columnIndex) throws SQLException
  {
    String s = getFixedString(columnIndex);

    if (s != null)
      {
	try
	  {
	    return Long.parseLong(s);
	  } catch (NumberFormatException e) {
	    throw new PSQLException ("postgresql.res.badlong",s);
	  }
      }
    return 0;		// SQL NULL
  }