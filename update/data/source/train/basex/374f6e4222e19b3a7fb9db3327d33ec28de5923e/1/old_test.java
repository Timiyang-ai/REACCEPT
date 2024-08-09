@Test
  public void delete() {
    for(final byte[] t : LIST) set.delete(t);
    for(final byte[] t : LIST) assertFalse("Token exists.", set.contains(t));
  }