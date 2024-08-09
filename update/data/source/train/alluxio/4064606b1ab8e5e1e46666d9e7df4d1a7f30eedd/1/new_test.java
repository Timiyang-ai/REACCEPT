@Test
  public void heartbeat() throws Exception {
    List<Long> persistedFiles = Lists.newArrayList(1L);
    Mockito.when(mFileDataManager.getPersistedFiles()).thenReturn(persistedFiles);
    // first time fails, second time passes
    Mockito.when(mFileSystemMasterClient.heartbeat(Mockito.anyLong(), Mockito.eq(persistedFiles)))
        .thenReturn(new FileSystemCommand());
    mFileWorkerMasterSyncExecutor.heartbeat();
    Mockito.verify(mFileDataManager).clearPersistedFiles(persistedFiles);
  }