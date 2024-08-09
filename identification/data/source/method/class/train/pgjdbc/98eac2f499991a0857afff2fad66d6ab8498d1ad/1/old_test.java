  public void testgetBoolean(String dataType) throws SQLException {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select 1::" + dataType + ", 0::" + dataType + ", 2::" + dataType);
    assertTrue(rs.next());
    assertEquals(true, rs.getBoolean(1));
    assertEquals(false, rs.getBoolean(2));

    try {
      // The JDBC ResultSet JavaDoc states that only 1 and 0 are valid values, so 2 should return error.
      rs.getBoolean(3);
      fail();
    } catch (SQLException e) {
      assertEquals(org.postgresql.util.PSQLState.CANNOT_COERCE.getState(), e.getSQLState());
      assertEquals("Cannot cast to boolean: \"2\"", e.getMessage());
    }
    rs.close();
    stmt.close();
  }