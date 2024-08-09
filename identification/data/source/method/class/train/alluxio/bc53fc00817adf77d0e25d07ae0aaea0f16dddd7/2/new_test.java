  @Test
  public void getNumberOfBlocks() {
    Assert.assertEquals(COMMITTED_BLOCKS_NUM, mBlockStoreMetaFull.getNumberOfBlocks());
  }