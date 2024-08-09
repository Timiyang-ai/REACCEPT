  @Test
  public void getUsedBytesOnTiers() {
    long usedBytes = TEST_BLOCK_SIZE * COMMITTED_BLOCKS_NUM;
    Map<String, Long> usedBytesOnTiers = ImmutableMap.of("MEM", usedBytes, "SSD", 0L);
    Assert.assertEquals(usedBytesOnTiers, mBlockStoreMeta.getUsedBytesOnTiers());
  }