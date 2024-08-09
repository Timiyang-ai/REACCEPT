@Test
  public void delete() {
    for(final byte[] token : LIST) set.delete(token);
    for(final byte[] token : LIST) assertFalse("Token exists.", set.contains(token));
  }