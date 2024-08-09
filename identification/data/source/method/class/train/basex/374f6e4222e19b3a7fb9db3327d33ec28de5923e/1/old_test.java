@Test
  public void add() {
    assertEquals(SIZE, set.size());
    for(final byte[] t : LIST) assertTrue("Token is missing.", set.contains(t));
  }