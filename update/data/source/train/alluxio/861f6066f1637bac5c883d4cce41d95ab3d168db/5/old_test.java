@Test
  public void setAttributeTest() throws Exception {
    TachyonURI file = new TachyonURI("/file");
    SetAttributeOptions setAttributeOptions = SetAttributeOptions.defaults();
    mFileSystem.setAttribute(file, setAttributeOptions);
    Mockito.verify(mFileSystemMasterClient).setAttribute(file, setAttributeOptions);
  }