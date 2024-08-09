public long getLong(int columnIndex) throws SQLException
  {
    return toLong( getFixedString(columnIndex) );
  }