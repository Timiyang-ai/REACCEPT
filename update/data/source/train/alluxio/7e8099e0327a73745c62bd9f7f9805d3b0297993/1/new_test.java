@Test
  public void removeBlocksTest() throws Exception {
    long worker1 = mMaster.getWorkerId(NET_ADDRESS_1);
    long worker2 = mMaster.getWorkerId(NET_ADDRESS_1);
    MasterWorkerInfo workerInfo1 = mPrivateAccess.getWorkerById(worker1);
    MasterWorkerInfo workerInfo2 = mPrivateAccess.getWorkerById(worker2);
    List<Long> workerBlocks = Arrays.asList(1L, 2L, 3L);
    HashMap<String, List<Long>> noBlocksInTiers = Maps.newHashMap();
    mMaster.workerRegister(worker1, Arrays.asList("MEM"), ImmutableMap.of("MEM", 100L),
        ImmutableMap.of("MEM", 0L), noBlocksInTiers);
    mMaster.workerRegister(worker2, Arrays.asList("MEM"), ImmutableMap.of("MEM", 100L),
        ImmutableMap.of("MEM", 0L), noBlocksInTiers);
    mMaster.commitBlock(worker1, 1L, "MEM", 1L, 1L);
    mMaster.commitBlock(worker1, 2L, "MEM", 2L, 1L);
    mMaster.commitBlock(worker1, 3L, "MEM", 3L, 1L);
    mMaster.commitBlock(worker2, 1L, "MEM", 1L, 1L);
    mMaster.commitBlock(worker2, 2L, "MEM", 2L, 1L);
    mMaster.commitBlock(worker2, 3L, "MEM", 3L, 1L);
    mMaster.removeBlocks(workerBlocks, false /* delete */);
    Assert.assertEquals(1L, mMaster.getBlockInfo(1L).getBlockId());

    // Test removeBlocks with delete
    mMaster.removeBlocks(workerBlocks, true /* delete */);

    // Update the heartbeat of removedBlockIds received from worker 1
    Command heartBeat1 = mMaster.workerHeartbeat(worker1,
        ImmutableMap.of("MEM", 20L, "SSD", 30L, "HDD", 50L),
        ImmutableList.of(1L, 2L, 3L), ImmutableMap.<String, List<Long>>of());
    // Verify removedBlockIds have been removed from ToRemoveBlocks on worker 1
    Assert.assertFalse(workerInfo1.getToRemoveBlocks().contains(1L));
    Assert.assertFalse(workerInfo1.getToRemoveBlocks().contains(2L));
    Assert.assertFalse(workerInfo1.getToRemoveBlocks().contains(3L));
    // Verify the muted Free command on worker1
    Assert.assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartBeat1);

    // Update the heartbeat of removedBlockIds received from worker 2
    Command heartBeat2 = mMaster.workerHeartbeat(worker2,
        ImmutableMap.of("MEM", 30L, "SSD", 50L, "HDD", 60L),
        ImmutableList.of(1L, 2L, 3L), ImmutableMap.<String, List<Long>>of());
    // Verify removedBlockIds have been removed from ToRemoveBlocks on worker2
    Assert.assertFalse(workerInfo2.getToRemoveBlocks().contains(1L));
    Assert.assertFalse(workerInfo2.getToRemoveBlocks().contains(2L));
    Assert.assertFalse(workerInfo2.getToRemoveBlocks().contains(3L));
    // Verify the muted Free command on worker2
    Assert.assertEquals(new Command(CommandType.Nothing, ImmutableList.<Long>of()), heartBeat2);

    mThrown.expect(BlockInfoException.class);
    mMaster.getBlockInfo(1L);
    Assert.assertFalse(mMaster.getLostBlocks().contains(1L));
    Assert.assertFalse(mMaster.getLostBlocks().contains(2L));
    Assert.assertFalse(mMaster.getLostBlocks().contains(3L));
  }