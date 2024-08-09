private void copy(String storeId, File src, File tgt, long capacityInBytes) throws Exception {
    try (StoreCopier copier = new StoreCopier(storeId, src, tgt, capacityInBytes, fetchSizeInBytes, storeConfig,
        new MetricRegistry(), storeKeyFactory, diskIOScheduler, transformers, time)) {
      copier.copy(new StoreFindTokenFactory(storeKeyFactory).getNewFindToken());
    }
    // verify that the stores are equivalent
    File[] replicas = {src, tgt};
    if (!consistencyChecker.checkConsistency(replicas)) {
      throw new IllegalStateException("Data in " + src + " and " + tgt + " is not equivalent");
    }
  }