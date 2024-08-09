  @Test
  public void testgetInt() throws SQLException {
    ResultSet rs = con.createStatement().executeQuery("select * from testnumeric");

    assertTrue(rs.next());
    assertEquals(1, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(0, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(-1, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(1, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(-2, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(99999, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(99999, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(-99999, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(-99999, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(Integer.MAX_VALUE, rs.getInt(1));

    assertTrue(rs.next());
    assertEquals(Integer.MIN_VALUE, rs.getInt(1));

    while (rs.next()) {
      try {
        rs.getInt(1);
        fail("Exception expected." + rs.getString(1));
      } catch (Exception e) {
      }
    }
    rs.close();
  }