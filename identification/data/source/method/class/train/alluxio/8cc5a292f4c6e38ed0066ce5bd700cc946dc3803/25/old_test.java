@Test
  public void heartbeat() throws Exception {
    List<Long> persistedFiles = Lists.newArrayList(1L);
    List<String> ufsFingerprintList = Lists.newArrayList("ufs fingerprint");
    FileDataManager.PersistedFilesInfo filesInfo =
        new FileDataManager.PersistedFilesInfo(persistedFiles, ufsFingerprintList);
    when(mFileDataManager.getPersistedFileInfos()).thenReturn(filesInfo);
    // first time fails, second time passes
    when(mFileSystemMasterClient.heartbeat(anyLong(), eq(persistedFiles)))
        .thenReturn(new FileSystemCommand());
    mFileWorkerMasterSyncExecutor.heartbeat();
    verify(mFileDataManager).clearPersistedFiles(persistedFiles);
  }