  @Test
  public void testgetLong() throws SQLException {
    ResultSet rs = con.createStatement().executeQuery("select * from testnumeric");

    assertTrue(rs.next());
    assertEquals(1, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(0, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(-1, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(1, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(-2, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(99999, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(99999, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(-99999, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(-99999, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals((Integer.MAX_VALUE), rs.getLong(1));

    assertTrue(rs.next());
    assertEquals((Integer.MIN_VALUE), rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(((long) Integer.MAX_VALUE) + 1, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(((long) Integer.MIN_VALUE) - 1, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(Long.MAX_VALUE, rs.getLong(1));

    assertTrue(rs.next());
    assertEquals(Long.MIN_VALUE, rs.getLong(1));

    while (rs.next()) {
      try {
        rs.getLong(1);
        fail("Exception expected." + rs.getString(1));
      } catch (Exception e) {
      }
    }
    rs.close();
  }