@Test
  public void getOutputStreamTest() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    UnderFileSystemManager manager = new UnderFileSystemManager();
    long id = manager.createFile(mSessionId, new AlluxioURI(uniqPath));
    Assert.assertEquals(mMockOutputStream, manager.getOutputStream(id));
  }