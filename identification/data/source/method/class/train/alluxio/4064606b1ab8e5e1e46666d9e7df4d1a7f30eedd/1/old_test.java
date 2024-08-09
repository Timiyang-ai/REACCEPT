@Test
  public void heartbeat() throws Exception {
    List<Long> persistedFiles = Lists.newArrayList(1L);
    Mockito.when(mFileDataManager.getPersistedFiles()).thenReturn(persistedFiles);
    // first time fails, second time passes
    Mockito.when(mFileSystemMasterClient.heartbeat(WorkerIdRegistry.getWorkerId(), persistedFiles))
        .thenReturn(new FileSystemCommand());
    mFileWorkerMasterSyncExecutor.heartbeat();
    Mockito.verify(mFileDataManager).clearPersistedFiles(persistedFiles);
  }