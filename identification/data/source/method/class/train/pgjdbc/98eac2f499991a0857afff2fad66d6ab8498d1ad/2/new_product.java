public boolean getBoolean(int columnIndex) throws SQLException
  {
	return toBoolean( getString(columnIndex) );
  }