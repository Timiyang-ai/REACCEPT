@Test
  public void free() throws Exception {
    mNestedFileContext.setPersisted(true);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    // free the file
    mFileSystemMaster.free(NESTED_FILE_URI, FreeContext.mergeFrom(FreePOptions.newBuilder()
        .setForced(false).setRecursive(false)));
    // Update the heartbeat of removedBlockId received from worker 1.
    Command heartbeat2 = mBlockMaster.workerHeartbeat(mWorkerId1, null,
        ImmutableMap.of("MEM", (long) Constants.KB), ImmutableList.of(blockId),
        ImmutableMap.<String, List<Long>>of(), mMetrics);
    // Verify the muted Free command on worker1.
    assertEquals(Command.newBuilder().setCommandType(CommandType.Nothing).build(), heartbeat2);
    assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
  }