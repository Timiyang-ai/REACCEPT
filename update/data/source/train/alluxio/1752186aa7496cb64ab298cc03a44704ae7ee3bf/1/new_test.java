@Test
  public void createUfsFileTest() throws Exception {
    mManager.createFile(SESSION_ID, mUri, Permission.defaults());
    Mockito.verify(mMockUfs).create(Mockito.contains(mUri.toString()),
        Mockito.any(CreateOptions.class));
    Mockito.verify(mMockUfs).connectFromWorker(Mockito.any(Configuration.class),
        Mockito.anyString());
  }