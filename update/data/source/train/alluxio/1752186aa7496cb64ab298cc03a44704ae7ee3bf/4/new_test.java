@Test
  public void completeUfsFileTest() throws Exception {
    long id = mManager.createFile(SESSION_ID, mUri,
        new Permission("", "", Constants.DEFAULT_FILE_SYSTEM_MODE));
    mManager.completeFile(SESSION_ID, id,
        new Permission("", "", Constants.DEFAULT_FILE_SYSTEM_MODE));
    Mockito.verify(mMockUfs).rename(Mockito.contains(mUri.toString()), Mockito.eq(mUri.toString()));
  }