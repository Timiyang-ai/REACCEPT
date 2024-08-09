@Test
  public void cancelUfsFileTest() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    UnderFileSystemManager manager = new UnderFileSystemManager();
    long id = manager.createFile(uniqPath);
    Mockito.verify(mMockUfs).create(Mockito.contains(uniqPath));
    manager.cancelFile(id);
    Mockito.verify(mMockUfs).delete(Mockito.contains(uniqPath), Mockito.eq(false));
  }