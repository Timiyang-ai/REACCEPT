@Test
  public void completeUfsFileTest() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    UnderFileSystemManager manager = new UnderFileSystemManager();
    long id = manager.createFile(SESSION_ID, new AlluxioURI(uniqPath));
    Mockito.verify(mMockUfs).create(Mockito.contains(uniqPath));
    manager.completeFile(SESSION_ID, id, null, null);
    Mockito.verify(mMockUfs).rename(Mockito.contains(uniqPath), Mockito.eq(uniqPath));
  }