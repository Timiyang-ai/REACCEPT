@Test
  public void getAuthorityTests() {
    String[] authorities =
        new String[] {"localhost", "localhost:8080", "127.0.0.1", "127.0.0.1:8080", "localhost",
            null};
    for (String authority : authorities) {
      AlluxioURI uri = new AlluxioURI("file", authority, "/a/b");
      assertEquals(authority, uri.getAuthority());
    }

    assertEquals(null, new AlluxioURI("file", "", "/b/c").getAuthority());
    assertEquals(null, new AlluxioURI("file", null, "/b/c").getAuthority());
    assertEquals(null, new AlluxioURI("file:///b/c").getAuthority());
  }