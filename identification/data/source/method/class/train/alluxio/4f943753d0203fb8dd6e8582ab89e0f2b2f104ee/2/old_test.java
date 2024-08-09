  @Test
  public void getBlockInfo() throws Exception {
    // Create a worker with a block.
    long worker1 = mBlockMaster.getWorkerId(NET_ADDRESS_1);
    long blockId = 1L;
    long blockLength = 20L;
    mBlockMaster.workerRegister(worker1, Arrays.asList("MEM"), ImmutableMap.of("MEM", 100L),
        ImmutableMap.of("MEM", 0L), NO_BLOCKS_ON_LOCATION, NO_LOST_STORAGE,
        RegisterWorkerPOptions.getDefaultInstance());
    mBlockMaster.commitBlock(worker1, 50L, "MEM", "MEM", blockId, blockLength);

    BlockLocation blockLocation = new BlockLocation()
        .setTierAlias("MEM")
        .setWorkerAddress(NET_ADDRESS_1)
        .setWorkerId(worker1)
        .setMediumType("MEM");
    BlockInfo expectedBlockInfo = new BlockInfo()
        .setBlockId(1L)
        .setLength(20L)
        .setLocations(ImmutableList.of(blockLocation));
    assertEquals(expectedBlockInfo, mBlockMaster.getBlockInfo(blockId));
  }