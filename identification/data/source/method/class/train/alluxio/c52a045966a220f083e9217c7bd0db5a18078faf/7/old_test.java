  @Test
  public void resizeTempBlockMeta() throws Exception {
    StorageDir dir = mMetaManager.getTier("MEM").getDir(0);
    TempBlockMeta tempBlockMeta =
        new TempBlockMeta(TEST_SESSION_ID, TEST_TEMP_BLOCK_ID, TEST_BLOCK_SIZE, dir);
    mMetaManager.resizeTempBlockMeta(tempBlockMeta, TEST_BLOCK_SIZE + 1);
    assertEquals(TEST_BLOCK_SIZE + 1, tempBlockMeta.getBlockSize());
  }