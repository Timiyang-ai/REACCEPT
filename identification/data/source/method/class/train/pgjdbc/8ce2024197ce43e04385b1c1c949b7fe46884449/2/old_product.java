public int getInt(int columnIndex) throws SQLException
  {
    String s = getFixedString(columnIndex);

    if (s != null)
      {
	try
	  {
	    return Integer.parseInt(s);
	  } catch (NumberFormatException e) {
	    throw new PSQLException ("postgresql.res.badint",s);
	  }
      }
    return 0;		// SQL NULL
  }