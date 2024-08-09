@Test
  public void commitBlock() throws Exception {
    TieredBlockStoreTestUtils.createTempBlock(SESSION_ID1, TEMP_BLOCK_ID, BLOCK_SIZE, mTestDir1);
    assertFalse(mBlockStore.hasBlockMeta(TEMP_BLOCK_ID));
    mBlockStore.commitBlock(SESSION_ID1, TEMP_BLOCK_ID, false);
    assertTrue(mBlockStore.hasBlockMeta(TEMP_BLOCK_ID));
    assertFalse(
        FileUtils.exists(TempBlockMeta.tempPath(mTestDir1, SESSION_ID1, TEMP_BLOCK_ID)));
    assertTrue(FileUtils.exists(TempBlockMeta.commitPath(mTestDir1, TEMP_BLOCK_ID)));
  }