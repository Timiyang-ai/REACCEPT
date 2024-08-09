@Test
  public void createUfsFileTest() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    UnderFileSystemManager manager = new UnderFileSystemManager();
    manager.createFile(uniqPath);
    Mockito.verify(mMockUfs).create(Mockito.contains(uniqPath));
  }