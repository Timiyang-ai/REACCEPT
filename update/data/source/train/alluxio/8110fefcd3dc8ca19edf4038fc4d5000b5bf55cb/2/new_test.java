@Test
  public void getInputStreamAtPositionTest() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    long position = 100L;
    Mockito.when(mMockUfs.exists(uniqPath)).thenReturn(true);
    Mockito.when(mMockInputStream.skip(position)).thenReturn(position);
    UnderFileSystemManager manager = new UnderFileSystemManager();
    long id = manager.openFile(mSessionId, new AlluxioURI(uniqPath));
    InputStream in = manager.getInputStreamAtPosition(id, position);
    Assert.assertEquals(mMockInputStream, in);
    Mockito.verify(mMockInputStream).skip(position);
    in.close();
  }