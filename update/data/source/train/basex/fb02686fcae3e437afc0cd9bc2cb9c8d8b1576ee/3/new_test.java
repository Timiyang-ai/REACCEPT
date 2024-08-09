@Test
  public void testFull() throws IOException {
    for(final Object[] exp : TYPES) {
      if(exp.length < 2) continue;
      try(final TestQuery tq = session.query(exp[1].toString())) {
        final TestResult tr = tq.full();
        final Object[] type = TYPES[tr.type];
        assertSame("Types are different.\nExpected: " + exp[0] + "\nFound: " + type[0], exp, type);
        assertEquals(Token.string(tr.result), type[2]);
        if(exp.length > 3) assertEquals(Token.string(tr.uri), type[3]);
      }
    }
  }