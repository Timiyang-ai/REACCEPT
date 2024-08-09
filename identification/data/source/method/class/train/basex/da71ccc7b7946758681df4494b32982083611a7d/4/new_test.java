@Test
  public void delete() {
    for(final byte[] token : LIST) set.remove(token);
    for(final byte[] token : LIST) assertFalse("Token exists.", set.contains(token));
  }