@Test
  public void testSeekWithCachingIncompleteBlocks() throws IOException {
    mTestStream = new FileInStream(mStatus,
        InStreamOptions.defaults().setReadType(ReadType.CACHE_PROMOTE)
            .enableCacheIncompleteBlock());
    int seekAmount = (int) (BLOCK_LENGTH / 2);
    // Seek to the mid of block 0.
    mTestStream.seek(seekAmount);
    // Seek to the mid of block 2.
    mTestStream.seek(seekAmount * 5);
    // Seek back to the mid of block 1.
    mTestStream.seek(seekAmount * 3);
    // Block 0, 2 should be cached till now.
    Assert.assertArrayEquals(BufferUtils.getIncreasingByteArray(0, (int) BLOCK_LENGTH),
        mCacheStreams.get(0).getWrittenData());
    Assert.assertArrayEquals(
        BufferUtils.getIncreasingByteArray((int) (2 * BLOCK_LENGTH), (int) BLOCK_LENGTH),
        mCacheStreams.get(2).getWrittenData());
    // The first half of block 1 is cached (not committed).
    Assert.assertArrayEquals(
        BufferUtils.getIncreasingByteArray((int) BLOCK_LENGTH, (int) BLOCK_LENGTH / 2),
        mCacheStreams.get(1).getWrittenData());

    // Seek forward within block 1 many times.
    for (int i = 0; i <= seekAmount / 2; ++i) {
      mTestStream.seek(seekAmount * 3 + i);
    }
    Assert.assertArrayEquals(
        BufferUtils.getIncreasingByteArray((int) BLOCK_LENGTH, (int) BLOCK_LENGTH / 4 * 3),
        mCacheStreams.get(1).getWrittenData());

    // Seek backward within block 1 many times.
    for (int i = seekAmount / 2; i >= 0; --i) {
      mTestStream.seek(seekAmount * 3 + i);
    }
    Assert.assertArrayEquals(
        BufferUtils.getIncreasingByteArray((int) BLOCK_LENGTH, (int) BLOCK_LENGTH),
        mCacheStreams.get(1).getWrittenData());

    // All the operations above on block 1 won't affect its adjacent blocks.
    Assert.assertArrayEquals(BufferUtils.getIncreasingByteArray(0, (int) BLOCK_LENGTH),
        mCacheStreams.get(0).getWrittenData());
    Assert.assertArrayEquals(
        BufferUtils.getIncreasingByteArray((int) (2 * BLOCK_LENGTH), (int) BLOCK_LENGTH),
        mCacheStreams.get(2).getWrittenData());
  }