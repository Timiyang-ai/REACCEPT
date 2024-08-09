@Test
  public void testFull() throws IOException {
    for(final Object[] t : TYPES) {
      if(t.length < 2) continue;
      try(final TestQuery tq = session.query(t[1].toString())) {
        final TestItem ti = tq.full();
        assertSame("Types are different.\nExpected: " + t[0] +
            "\nFound: " + TYPES[ti.type][0], t, TYPES[ti.type]);
        assertEquals(Token.string(ti.result), TYPES[ti.type][2]);
        if(t.length > 3) assertEquals(Token.string(ti.uri), TYPES[ti.type][3]);
      }
    }
  }