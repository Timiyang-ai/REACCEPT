  @Test
  public void flush() throws Exception {
    FileOutStream fos = mock(FileOutStream.class);
    AlluxioURI anyURI = any();
    CreateFilePOptions options = any();
    when(mFileSystem.createFile(anyURI, options)).thenReturn(fos);

    // open a file
    mFileInfo.flags.set(O_WRONLY.intValue());
    mFuseFs.create("/foo/bar", 0, mFileInfo);

    // then call flush into it
    mFuseFs.flush("/foo/bar", mFileInfo);
    verify(fos).flush();
  }