@Test
  public void delete() {
    for(final byte[] t : LIST) set.delete(t);
    for(final byte[] t : LIST)
      assertTrue("Token should not be contained in list.", set.id(t) == 0);
  }