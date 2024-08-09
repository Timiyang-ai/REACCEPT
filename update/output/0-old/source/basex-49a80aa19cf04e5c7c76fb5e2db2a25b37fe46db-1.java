@Test
  public void isAbsolute() {
    assertUriIsAbsolute("x:", true);

    // absolute URIs always have schema
    assertUriIsAbsolute("x", false);
    assertUriIsAbsolute("", false);
    // [DP] #928
    //assertUriIsAbsolute("//localhost:80", false);

    // absolute URIs don't have fragments
    // [DP] #928
    //assertUriIsAbsolute("http://localhost:80/html#f", false);
  }