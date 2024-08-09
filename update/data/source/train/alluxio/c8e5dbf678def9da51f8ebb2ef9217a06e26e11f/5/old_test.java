@Test
  public void getBlockLocation() {
    BlockStoreLocation expectedLocation =
        new BlockStoreLocation(mTier.getTierAlias(), mDir.getDirIndex());
    Assert.assertEquals(expectedLocation, mBlockMeta.getBlockLocation());
  }