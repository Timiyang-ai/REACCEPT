@Test
  public void testParse() {
    for(final String wc : VALIDWC) assertTrue(new FTWildcard(token(wc)).parse());
    for(final String wc : INVALIDWC) assertFalse(new FTWildcard(token(wc)).parse());
  }