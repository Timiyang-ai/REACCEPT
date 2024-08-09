@Test
  public void free() throws Exception {
    mNestedFileOptions.setPersisted(true);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    // free the file
    mFileSystemMaster.free(NESTED_FILE_URI,
        FreeOptions.defaults().setForced(false).setRecursive(false));
    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat2 = mBlockMaster.workerHeartbeat(mWorkerId1, null,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat2);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }