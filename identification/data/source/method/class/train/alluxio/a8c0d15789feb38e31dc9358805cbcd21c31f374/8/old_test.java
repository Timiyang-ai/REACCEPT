@Test
  public void freeTest() throws Exception {
    mFileSystemMaster.create(NESTED_FILE_URI, sNestedFileOptions);
    long blockId = writeBlockForFile(NESTED_FILE_URI);
    Assert.assertEquals(1, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    // cannot free directory with recursive argument to false
    Assert.assertFalse(mFileSystemMaster.free(NESTED_FILE_URI.getParent(), false));

    Assert.assertEquals(1, mCounters.get(MasterSource.FREE_FILE_OPS).getCount());
    Assert.assertEquals(0, mCounters.get(MasterSource.FILES_FREED).getCount());

    // free the file
    Assert.assertTrue(mFileSystemMaster.free(NESTED_FILE_URI, false));
    // Update the heartbeat of removedBlockId received from worker 1
    Command heartBeat2 = mBlockMaster.workerHeartbeat(mWorkerId,
        ImmutableMap.of("MEM", Constants.KB * 1L),
        ImmutableList.of(blockId), ImmutableMap.<String, List<Long>>of());
    // Verify the muted Free command on worker
    Assert.assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartBeat2);
    Assert.assertEquals(0, mBlockMaster.getBlockInfo(blockId).getLocations().size());

    Assert.assertEquals(2, mCounters.get(MasterSource.FREE_FILE_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.FILES_FREED).getCount());
  }