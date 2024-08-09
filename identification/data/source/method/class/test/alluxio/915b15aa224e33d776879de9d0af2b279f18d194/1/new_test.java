  @Test
  public void createTempBlockMeta() {
    TempBlockMeta tempBlockMeta =
        mTestDirView.createTempBlockMeta(TEST_SESSION_ID, TEST_TEMP_BLOCK_ID, TEST_BLOCK_SIZE);
    Assert.assertEquals(TEST_SESSION_ID, tempBlockMeta.getSessionId());
    Assert.assertEquals(TEST_TEMP_BLOCK_ID, tempBlockMeta.getBlockId());
    Assert.assertEquals(TEST_BLOCK_SIZE, tempBlockMeta.getBlockSize());
    Assert.assertEquals(mTestDir, tempBlockMeta.getParentDir());
  }