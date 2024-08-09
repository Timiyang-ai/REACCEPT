@Test
  public void isAbsolute() {
    assertEquals("Uri absolute check failed", absolute, Uri.uri(uri).isAbsolute());
  }