  @Test
  public void getBlockList() {
    Map<String, List<Long>> tierAliasToBlockIds = new HashMap<>();
    for (StorageTier tier : mMetadataManager.getTiers()) {
      List<Long> blockIdsOnTier = new ArrayList<>();
      for (StorageDir dir : tier.getStorageDirs()) {
        blockIdsOnTier.addAll(dir.getBlockIds());
      }
      tierAliasToBlockIds.put(tier.getTierAlias(), blockIdsOnTier);
    }
    Map<String, List<Long>> actual = mBlockStoreMetaFull.getBlockList();
    Assert.assertEquals(TieredBlockStoreTestUtils.TIER_ALIAS.length, actual.keySet().size());
    Assert.assertEquals(tierAliasToBlockIds, actual);
  }