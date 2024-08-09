  @Test
  public void deleteFile() throws Exception {
    // cannot delete root
    try {
      mFileSystemMaster.delete(ROOT_URI,
          DeleteContext.mergeFrom(DeletePOptions.newBuilder().setRecursive(true)));
      fail("Should not have been able to delete the root");
    } catch (InvalidPathException e) {
      assertEquals(ExceptionMessage.DELETE_ROOT_DIRECTORY.getMessage(), e.getMessage());
    }

    // delete the file
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    mFileSystemMaster.delete(NESTED_FILE_URI, DeleteContext.defaults());

    try {
      mBlockMaster.getBlockInfo(blockId);
      fail("Expected blockInfo to fail");
    } catch (BlockInfoException e) {
      // expected
    }

    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat1 = mBlockMaster.workerHeartbeat(mWorkerId1, null,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.of(), ImmutableMap.of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(Command.newBuilder().setCommandType(CommandType.Nothing).build(), heartbeat1);
    assertFalse(mBlockMaster.getLostBlocks().contains(blockId));

    // verify the file is deleted
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE_URI));

    AlluxioURI ufsMount = new AlluxioURI(mTestFolder.newFolder().getAbsolutePath());
    mFileSystemMaster.createDirectory(new AlluxioURI("/mnt/"), CreateDirectoryContext.defaults());
    // Create ufs file.
    Files.createDirectory(Paths.get(ufsMount.join("dir1").getPath()));
    Files.createFile(Paths.get(ufsMount.join("dir1").join("file1").getPath()));
    mFileSystemMaster.mount(new AlluxioURI("/mnt/local"), ufsMount,
        MountContext.defaults());

    AlluxioURI uri = new AlluxioURI("/mnt/local/dir1");
    mFileSystemMaster.listStatus(uri, ListStatusContext
        .mergeFrom(ListStatusPOptions.newBuilder().setLoadMetadataType(LoadMetadataPType.ALWAYS)));
    mFileSystemMaster.delete(new AlluxioURI("/mnt/local/dir1/file1"),
        DeleteContext.mergeFrom(DeletePOptions.newBuilder().setAlluxioOnly(true)));

    // ufs file still exists
    assertTrue(Files.exists(Paths.get(ufsMount.join("dir1").join("file1").getPath())));
    // verify the file is deleted
    mThrown.expect(FileDoesNotExistException.class);
    mFileSystemMaster.getFileInfo(new AlluxioURI("/mnt/local/dir1/file1"), GetStatusContext
        .mergeFrom(GetStatusPOptions.newBuilder().setLoadMetadataType(LoadMetadataPType.NEVER)));
  }