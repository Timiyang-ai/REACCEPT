public static StorageDir newStorageDir(StorageTier tier, int dirIndex, long capacityBytes,
      String dirPath, String dirMedium)
      throws BlockAlreadyExistsException, IOException, WorkerOutOfSpaceException {
    StorageDir dir = new StorageDir(tier, dirIndex, capacityBytes, dirPath, dirMedium);
    dir.initializeMeta();
    return dir;
  }