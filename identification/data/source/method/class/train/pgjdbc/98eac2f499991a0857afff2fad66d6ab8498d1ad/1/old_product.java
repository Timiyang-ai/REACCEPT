public boolean getBoolean(int columnIndex) throws SQLException
  {
    String s = getString(columnIndex);

    if (s != null)
      {
	int c = s.charAt(0);
	return ((c == 't') || (c == 'T'));
      }
    return false;		// SQL NULL
  }