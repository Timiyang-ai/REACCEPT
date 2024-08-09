  @Test
  public void serviceUri() throws Exception {
    GoogleAuthLibraryCallCredentials callCredentials =
        new GoogleAuthLibraryCallCredentials(credentials);
    callCredentials.applyRequestMetadata(
        new RequestInfoImpl("example.com:443"), executor, applier);
    verify(credentials).getRequestMetadata(eq(new URI("https://example.com/a.service")));

    callCredentials.applyRequestMetadata(
        new RequestInfoImpl("example.com:123"), executor, applier);
    verify(credentials).getRequestMetadata(eq(new URI("https://example.com:123/a.service")));
  }