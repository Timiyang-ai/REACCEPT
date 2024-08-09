@Test
  public void completeUfsFileTest() throws Exception {
    long id = mManager.createFile(SESSION_ID, mUri,
        new PermissionStatus("", "", Constants.DEFAULT_FS_FULL_PERMISSION));
    mManager.completeFile(SESSION_ID, id,
        new PermissionStatus("", "", Constants.DEFAULT_FS_FULL_PERMISSION));
    Mockito.verify(mMockUfs).rename(Mockito.contains(mUri.toString()), Mockito.eq(mUri.toString()));
  }