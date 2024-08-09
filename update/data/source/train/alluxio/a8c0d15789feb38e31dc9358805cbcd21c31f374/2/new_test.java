@Test
  public void deleteFileTest() throws Exception {
    // cannot delete root
    Assert.assertFalse(mFileSystemMaster.delete(ROOT_URI, true));

    // delete the file
    long blockId = createFileWithSingleBlock(NESTED_FILE_URI);
    Assert.assertTrue(
        mFileSystemMaster.delete(NESTED_FILE_URI, false));

    mThrown.expect(BlockInfoException.class);
    mBlockMaster.getBlockInfo(blockId);

    // Update the heartbeat of removedBlockId received from worker 1
    Command heartBeat1 = mBlockMaster.workerHeartbeat(mWorkerId1,
        ImmutableMap.of("MEM", Constants.KB * 1L),
        ImmutableList.of(blockId), ImmutableMap.<String, List<Long>>of());
    // Verify the muted Free command on worker1
    Assert.assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartBeat1);
    Assert.assertFalse(mBlockMaster.getLostBlocks().contains(blockId));

    // verify the file is deleted
    Assert.assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE_URI));
  }