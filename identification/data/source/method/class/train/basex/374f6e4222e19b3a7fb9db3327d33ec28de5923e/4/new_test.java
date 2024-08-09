@Test
  public void add() {
    assertEquals(SIZE, set.size());
    for(final byte[] token : LIST) assertTrue("Token is missing.", set.contains(token));
  }