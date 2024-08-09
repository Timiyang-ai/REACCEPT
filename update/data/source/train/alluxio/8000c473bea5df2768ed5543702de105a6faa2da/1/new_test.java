@Test
  public void setAttribute() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    SetAttributePOptions setAttributeOptions =
        FileSystemOptions.setAttributeClientDefaults(mFileContext.getPathConf(file));
    mFileSystem.setAttribute(file, setAttributeOptions);
    verify(mFileSystemMasterClient).setAttribute(file, setAttributeOptions);
  }