  @Test
  public void chmod() throws Exception {
    long mode = 123;
    mFuseFs.chmod("/foo/bar", mode);
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    SetAttributePOptions options =
        SetAttributePOptions.newBuilder().setMode(new Mode((short) mode).toProto()).build();
    verify(mFileSystem).setAttribute(expectedPath, options);
  }