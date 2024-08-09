@Test
  public void completeUfsFileTest() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    UnderFileSystemManager manager = new UnderFileSystemManager();
    long id = manager.createFile(uniqPath);
    Mockito.verify(mMockUfs).create(Mockito.contains(uniqPath));
    manager.completeFile(id);
    Mockito.verify(mMockUfs).rename(Mockito.contains(uniqPath), Mockito.eq(uniqPath));
  }