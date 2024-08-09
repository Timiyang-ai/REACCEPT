  @Test
  public void create() throws Exception {
    mFileInfo.flags.set(O_WRONLY.intValue());
    mFuseFs.create("/foo/bar", 0, mFileInfo);
    AlluxioURI expectedPath = BASE_EXPECTED_URI.join("/foo/bar");
    verify(mFileSystem).createFile(expectedPath, CreateFilePOptions.newBuilder()
        .setMode(new alluxio.security.authorization.Mode((short) 0).toProto())
        .build());
  }