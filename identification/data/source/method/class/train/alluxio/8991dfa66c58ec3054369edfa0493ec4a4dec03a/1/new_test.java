  @Test
  public void toStringTest() {
    assertEquals("rwxrwxrwx", new Mode((short) 0777).toString());
    assertEquals("rw-r-----", new Mode((short) 0640).toString());
    assertEquals("rw-------", new Mode((short) 0600).toString());
    assertEquals("---------", new Mode((short) 0000).toString());
  }