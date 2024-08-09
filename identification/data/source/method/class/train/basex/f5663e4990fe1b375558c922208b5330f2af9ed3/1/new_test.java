@Test
  public void delete() {
    for(final byte[] t : LIST) set.delete(t);
    for(final byte[] t : LIST)
      assertEquals("Token should not be contained in list.", 0, set.id(t));
  }