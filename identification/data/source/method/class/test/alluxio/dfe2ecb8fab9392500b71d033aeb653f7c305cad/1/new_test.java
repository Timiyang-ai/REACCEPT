  @Test
  public void getAuthorityTests() {
    String[] authorities =
        new String[] {"localhost", "localhost:8080", "127.0.0.1", "127.0.0.1:8080", "localhost"};
    for (String authority : authorities) {
      AlluxioURI uri = new AlluxioURI("file", Authority.fromString(authority), "/a/b");
      assertEquals(authority, uri.getAuthority().toString());
    }

    assertEquals("",
        new AlluxioURI("file", Authority.fromString(""), "/b/c").getAuthority().toString());
    assertEquals("", new AlluxioURI("file", null, "/b/c").getAuthority().toString());
    assertEquals("",
        new AlluxioURI("file", Authority.fromString(null), "/b/c").getAuthority().toString());
    assertEquals("", new AlluxioURI("file:///b/c").getAuthority().toString());
  }