  @Test
  public void hasScheme() {
    assertFalse(new AlluxioURI("/").hasScheme());
    assertTrue(new AlluxioURI("file:/").hasScheme());
    assertTrue(new AlluxioURI("file://localhost/").hasScheme());
    assertTrue(new AlluxioURI("file://localhost:8080/").hasScheme());
    assertFalse(new AlluxioURI("//localhost:8080/").hasScheme());
  }