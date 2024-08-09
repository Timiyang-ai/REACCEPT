  @Test
  public void register() {
    assertEquals(NEW_BLOCKS, mInfo.getBlocks());
    assertEquals(TOTAL_BYTES_ON_TIERS, mInfo.getTotalBytesOnTiers());
    assertEquals(Constants.KB * 6L, mInfo.getCapacityBytes());
    assertEquals(USED_BYTES_ON_TIERS, mInfo.getUsedBytesOnTiers());
    assertEquals(Constants.KB * 2L, mInfo.getUsedBytes());
  }