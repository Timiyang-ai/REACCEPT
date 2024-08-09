  @Test
  public void getUsedBytes() {
    long usedBytes = TEST_BLOCK_SIZE * COMMITTED_BLOCKS_NUM;
    Assert.assertEquals(usedBytes, mBlockStoreMeta.getUsedBytes());
  }