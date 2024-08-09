@Test
  public void createUfsFile() throws Exception {
    mManager.createFile(SESSION_ID, mUri, CreateUfsFileOptions.defaults());
    Mockito.verify(mMockUfs).create(Mockito.contains(mUri.toString()),
        Mockito.any(CreateOptions.class));
    Mockito.verify(mMockUfs).connectFromWorker(Mockito.anyString());
  }