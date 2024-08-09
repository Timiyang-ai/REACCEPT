@Test
  public void getBlockLocation() {
    BlockStoreLocation expectedLocation =
        new BlockStoreLocation(mTier.getTierAlias(), mDir.getDirIndex(), mDir.getDirMedium());
    Assert.assertEquals(expectedLocation, mBlockMeta.getBlockLocation());
  }