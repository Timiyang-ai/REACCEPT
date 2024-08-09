@Test
  public void workerHeartbeat() throws Exception {
    long blockId = createFileWithSingleBlock(ROOT_FILE_URI);

    long fileId = mFileSystemMaster.getFileId(ROOT_FILE_URI);
    mFileSystemMaster.scheduleAsyncPersistence(ROOT_FILE_URI);

    FileSystemCommand command = mFileSystemMaster
        .workerHeartbeat(mWorkerId1, Lists.newArrayList(fileId), WorkerHeartbeatOptions.defaults());
    assertEquals(CommandType.Persist, command.getCommandType());
    assertEquals(0, command.getCommandOptions().getPersistOptions().getPersistFiles().size());
  }