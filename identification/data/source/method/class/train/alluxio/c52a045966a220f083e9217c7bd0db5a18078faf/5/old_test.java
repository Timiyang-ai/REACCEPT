  @Test
  public void getAvailableBytes() {
    assertEquals(9000, mMetaManager.getAvailableBytes(BlockStoreLocation.anyTier()));
    assertEquals(1000,
        mMetaManager.getAvailableBytes(BlockStoreLocation.anyDirInTier("MEM")));
    assertEquals(8000,
        mMetaManager.getAvailableBytes(BlockStoreLocation.anyDirInTier("HDD")));
    assertEquals(1000, mMetaManager.getAvailableBytes(new BlockStoreLocation("MEM", 0)));
    assertEquals(3000, mMetaManager.getAvailableBytes(new BlockStoreLocation("HDD", 0)));
    assertEquals(5000, mMetaManager.getAvailableBytes(new BlockStoreLocation("HDD", 1)));
  }