@Test
  public void rename() throws Exception {
    AlluxioURI src = new AlluxioURI("/file");
    AlluxioURI dst = new AlluxioURI("/file2");
    RenamePOptions renameOptions = RenamePOptions.getDefaultInstance();
    doNothing().when(mFileSystemMasterClient).rename(src, dst,
        FileSystemOptions.renameDefaults(mConf).toBuilder().mergeFrom(renameOptions).build());
    mFileSystem.rename(src, dst, renameOptions);
    verify(mFileSystemMasterClient).rename(src, dst,
        FileSystemOptions.renameDefaults(mConf).toBuilder().mergeFrom(renameOptions).build());
  }