@Test
  public void toBlockStoreLocation() {
    StorageTier tier = mDir.getParentTier();
    assertEquals(new BlockStoreLocation(tier.getTierAlias(), mDir.getDirIndex()),
        mDir.toBlockStoreLocation());
  }