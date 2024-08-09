@Test
  public void free() throws Exception {
    sNestedFileOptions.setPersisted(true);
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    Assert.assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    // free the file
    Assert.assertTrue(mFileSystemMaster.free(NESTED_FILE_URI, false));
    // Update the heartbeat of removedBlockId received from worker 1
    Command heartbeat2 =
        mBlockMaster.workerHeartbeat(mWorkerId1, ImmutableMap.of("MEM", (long) Constants.KB),
            ImmutableList.of(blockId), ImmutableMap.<String, List<Long>>of());
    // Verify the muted Free command on worker1
    Assert.assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartbeat2);
    Assert.assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());
    sNestedFileOptions.setPersisted(false);
  }