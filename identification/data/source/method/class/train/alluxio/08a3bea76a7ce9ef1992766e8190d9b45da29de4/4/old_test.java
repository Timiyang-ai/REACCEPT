@Test
  public void hasAuthorityTests() {
    assertFalse(new AlluxioURI(".").hasAuthority());
    assertFalse(new AlluxioURI("/").hasAuthority());
    assertFalse(new AlluxioURI("file:/").hasAuthority());
    assertFalse(new AlluxioURI("file:///test").hasAuthority());
    assertTrue(new AlluxioURI("file://localhost/").hasAuthority());
    assertTrue(new AlluxioURI("file://localhost:8080/").hasAuthority());
    assertTrue(new AlluxioURI(null, "localhost:8080", "/").hasAuthority());
    assertTrue(new AlluxioURI(null, "localhost", "/").hasAuthority());
  }