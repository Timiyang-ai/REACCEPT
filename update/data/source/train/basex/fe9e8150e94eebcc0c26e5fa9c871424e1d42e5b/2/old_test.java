@Test
  public void testMatch() throws QueryException {
    for(int i = 0; i < VALIDWC.length; i++) {

      final String q = VALIDWC[i];
      final FTWildcard wc = new FTWildcard(token(q), null);

      final String[] good = TEXTS_GOOD[i];
      for(final String element : good) {
        assertTrue("\"" + q + "\" did NOT match \"" + element + "\"",
            wc.match(token(element)));
      }

      final String[] bad = TEXTS_BAD[i];
      for(final String element : bad) {
        assertFalse("\"" + q + "\" matched \"" + element + "\"",
            wc.match(token(element)));
      }
    }
  }