@Test
  public void testIter() throws IOException {
    for(final Object[] t : TYPES) {
      if(t.length < 2) continue;
      try(final TestQuery tq = session.query(t[1].toString())) {
        final TestItem ti = tq.iter();
        assertSame("Types are different.\nExpected: " + t[0] +
            "\nFound: " + TYPES[ti.type][0], t, TYPES[ti.type]);
        assertEquals(Token.string(ti.result), TYPES[ti.type][2]);
      }
    }
  }