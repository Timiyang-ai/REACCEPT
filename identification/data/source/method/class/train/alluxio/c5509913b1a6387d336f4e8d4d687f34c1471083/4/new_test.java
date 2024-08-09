  @Test
  public void getUsedBytesOnDirs() {
    Map<Pair<String, String>, Long> dirsToUsedBytes = new HashMap<>();
    for (StorageTier tier : mMetadataManager.getTiers()) {
      for (StorageDir dir : tier.getStorageDirs()) {
        dirsToUsedBytes.put(new Pair<>(tier.getTierAlias(), dir.getDirPath()),
            dir.getCapacityBytes() - dir.getAvailableBytes());
      }
    }
    Assert.assertEquals(dirsToUsedBytes, mBlockStoreMeta.getUsedBytesOnDirs());
  }