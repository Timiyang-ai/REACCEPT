  @Test
  public void getBlockIds() throws Exception {
    long blockId1 = TEST_BLOCK_ID + 1;
    long blockId2 = TEST_BLOCK_ID + 2;

    BlockMeta blockMeta1 = new BlockMeta(blockId1, TEST_BLOCK_SIZE, mDir);
    BlockMeta blockMeta2 = new BlockMeta(blockId2, TEST_BLOCK_SIZE, mDir);
    mDir.addBlockMeta(blockMeta1);
    mDir.addBlockMeta(blockMeta2);

    List<Long> actual = mDir.getBlockIds();
    assertEquals(Sets.newHashSet(blockId1, blockId2), new HashSet<>(actual));
  }