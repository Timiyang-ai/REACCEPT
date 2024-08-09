public void deleteStoreFiles() throws StoreException, IOException {
    // Step 0: ensure the store has been shut down
    if (started) {
      throw new IllegalStateException("Store is still started. Deleting store files is not allowed.");
    }
    // Step 1: return occupied swap segments (if any) to reserve pool
    String[] swapSegmentsInUse = compactor.getSwapSegmentsInUse();
    for (String fileName : swapSegmentsInUse) {
      logger.info("Returning swap segment {} to reserve pool", fileName);
      File swapSegmentTempFile = new File(dataDir, fileName);
      diskSpaceAllocator.free(swapSegmentTempFile, config.storeSegmentSizeInBytes, storeId, true);
    }
    // Step 2: delete all files
    logger.info("Deleting store {} directory", storeId);
    File storeDir = new File(dataDir);
    try {
      Utils.deleteFileOrDirectory(storeDir);
    } catch (Exception e) {
      throw new IOException("Couldn't delete store directory " + dataDir, e);
    }
    logger.info("All files of store {} deleted", storeId);
  }