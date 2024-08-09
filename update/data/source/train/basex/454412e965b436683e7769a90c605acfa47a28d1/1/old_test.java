@Test
  public void testParse() {
    for(final String wc : VALIDWC)
      assertTrue(new FTWildcard().parse(token(wc)));

    for(final String wc : INVALIDWC)
      assertFalse(new FTWildcard().parse(token(wc)));
  }