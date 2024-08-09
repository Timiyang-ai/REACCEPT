  @Test
  public void resizeTempBlockMeta() throws Exception {
    mDir.addTempBlockMeta(mTempBlockMeta);
    assertEquals(TEST_DIR_CAPACITY - TEST_TEMP_BLOCK_SIZE, mDir.getAvailableBytes());
    final long newSize = TEST_TEMP_BLOCK_SIZE + 10;
    mDir.resizeTempBlockMeta(mTempBlockMeta, newSize);
    assertEquals(TEST_DIR_CAPACITY - newSize, mDir.getAvailableBytes());
  }