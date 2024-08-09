  @Test
  public void getCapacityBytesOnDirs() {
    Map<Pair<String, String>, Long> dirsToCapacityBytes = new HashMap<>();
    for (StorageTier tier : mMetadataManager.getTiers()) {
      for (StorageDir dir : tier.getStorageDirs()) {
        dirsToCapacityBytes.put(new Pair<>(tier.getTierAlias(), dir.getDirPath()),
            dir.getCapacityBytes());
      }
    }
    Assert.assertEquals(dirsToCapacityBytes, mBlockStoreMeta.getCapacityBytesOnDirs());
    Assert.assertEquals(TieredBlockStoreTestUtils.getDefaultDirNum(), mBlockStoreMeta
        .getCapacityBytesOnDirs().values().size());
  }