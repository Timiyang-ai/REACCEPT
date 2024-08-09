@Test
  public void setAttributeTest() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    SetAttributeOptions setAttributeOptions = SetAttributeOptions.defaults();
    mFileSystem.setAttribute(file, setAttributeOptions);
    Mockito.verify(mFileSystemMasterClient).setAttribute(file, setAttributeOptions);
  }