@Test
  public void getNumberOfBlocksTest() {
    Assert.assertEquals(COMMITTED_BLOCKS_NUM, mBlockStoreMeta.getNumberOfBlocks());
  }