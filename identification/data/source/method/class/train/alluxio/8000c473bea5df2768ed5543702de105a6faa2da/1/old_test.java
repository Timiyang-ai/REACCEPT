@Test
  public void setAttribute() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    SetAttributePOptions setAttributeOptions = SetAttributePOptions.getDefaultInstance();
    mFileSystem.setAttribute(file, setAttributeOptions);
    verify(mFileSystemMasterClient).setAttribute(file, setAttributeOptions);
  }