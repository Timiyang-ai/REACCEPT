  @Test
  public void getBlocks() throws Exception {
    long blockId1 = TEST_BLOCK_ID + 1;
    long blockId2 = TEST_BLOCK_ID + 2;

    BlockMeta blockMeta1 = new BlockMeta(blockId1, TEST_BLOCK_SIZE, mDir);
    BlockMeta blockMeta2 = new BlockMeta(blockId2, TEST_BLOCK_SIZE, mDir);
    mDir.addBlockMeta(blockMeta1);
    mDir.addBlockMeta(blockMeta2);

    List<BlockMeta> actual = mDir.getBlocks();
    assertEquals(Sets.newHashSet(blockMeta1, blockMeta2), new HashSet<>(actual));
  }