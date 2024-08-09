@Test
  public void testMatch() throws QueryException {
    for(int i = 0; i < VALIDWC.length; i++) {

      final String q = VALIDWC[i];
      final FTWildcard wc = new FTWildcard(token(q), null);

      final String[] good = TEXTS_GOOD[i];
      for(int j = 0; j < good.length; j++) {
        assertTrue("\"" + q + "\" did NOT match \"" + good[j] + "\"",
            wc.match(token(good[j])));
      }

      final String[] bad = TEXTS_BAD[i];
      for(int j = 0; j < bad.length; j++) {
        assertFalse("\"" + q + "\" matched \"" + bad[j] + "\"",
            wc.match(token(bad[j])));
      }
    }
  }