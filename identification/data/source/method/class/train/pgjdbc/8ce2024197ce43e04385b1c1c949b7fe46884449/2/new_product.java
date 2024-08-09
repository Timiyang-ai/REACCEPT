public int getInt(int columnIndex) throws SQLException
  {
    return toInt( getFixedString(columnIndex) );
  }