  @Test
  public void getEvictableBlocks() throws Exception {
    // When test dir is empty, expect no block to be evictable
    Assert.assertEquals(0, mTestDirView.getEvitableBytes());
    Assert.assertTrue(mTestDirView.getEvictableBlocks().isEmpty());

    // Add one block to test dir, expect this block to be evictable
    BlockMeta blockMeta = new BlockMeta(TEST_BLOCK_ID, TEST_BLOCK_SIZE, mTestDir);
    mTestDir.addBlockMeta(blockMeta);
    Assert.assertEquals(TEST_BLOCK_SIZE, mTestDirView.getEvitableBytes());
    Assert.assertThat(mTestDirView.getEvictableBlocks(),
        CoreMatchers.is((List<BlockMeta>) Lists.newArrayList(blockMeta)));

    // Lock this block, expect this block to be non-evictable
    Mockito.when(mMetadataView.isBlockPinned(TEST_BLOCK_ID)).thenReturn(false);
    Mockito.when(mMetadataView.isBlockLocked(TEST_BLOCK_ID)).thenReturn(true);
    Assert.assertEquals(0, mTestDirView.getEvitableBytes());
    Assert.assertTrue(mTestDirView.getEvictableBlocks().isEmpty());

    // Pin this block, expect this block to be non-evictable
    Mockito.when(mMetadataView.isBlockPinned(TEST_BLOCK_ID)).thenReturn(true);
    Mockito.when(mMetadataView.isBlockLocked(TEST_BLOCK_ID)).thenReturn(false);
    Assert.assertEquals(0, mTestDirView.getEvitableBytes());
    Assert.assertTrue(mTestDirView.getEvictableBlocks().isEmpty());

    // Release pin/lock, expect this block to be evictable
    Mockito.when(mMetadataView.isBlockPinned(TEST_BLOCK_ID)).thenReturn(false);
    Mockito.when(mMetadataView.isBlockLocked(TEST_BLOCK_ID)).thenReturn(false);
    Assert.assertEquals(TEST_BLOCK_SIZE, mTestDirView.getEvitableBytes());
    Assert.assertThat(mTestDirView.getEvictableBlocks(),
        CoreMatchers.is((List<BlockMeta>) Lists.newArrayList(blockMeta)));
  }