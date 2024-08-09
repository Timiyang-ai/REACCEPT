@Test
  public void createUfsFileTest() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    UnderFileSystemManager manager = new UnderFileSystemManager();
    manager.createFile(new AlluxioURI(uniqPath));
    Mockito.verify(mMockUfs).create(Mockito.contains(uniqPath));
  }