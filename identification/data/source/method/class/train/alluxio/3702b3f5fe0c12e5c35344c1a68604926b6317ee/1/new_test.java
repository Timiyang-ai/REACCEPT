@Test
  public void removeBlocksTest() throws Exception {
    long worker1 = mMaster.getWorkerId(NET_ADDRESS_1);
    long worker2 = mMaster.getWorkerId(NET_ADDRESS_1);
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
    mThrown.expect(BlockInfoException.class);
    mMaster.getBlockInfo(1L);
    Assert.assertFalse(mMaster.getLostBlocks().contains(1L));
  }