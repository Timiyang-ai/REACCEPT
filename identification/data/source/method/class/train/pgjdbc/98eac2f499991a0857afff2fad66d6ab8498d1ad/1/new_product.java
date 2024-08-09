public boolean getBoolean(int columnIndex) throws SQLException
  {
    String s = getString(columnIndex);

    if (s != null)
      {
	int c = s.charAt(0);
	return ((c == 't') || (c == 'T') || (c == '1'));
      }
    return false;		// SQL NULL
  }