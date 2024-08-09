@Test
  public void testGetTrimmedWords() {
    a.setWords(new long[] {-1L, 0L}, 64);
    assertTrue("Array not trimmed correctly", a.getTrimmedWords().length == 1);
  }