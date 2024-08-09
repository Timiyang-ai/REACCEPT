public static StorageDir newStorageDir(StorageTier tier, int dirIndex, long capacityBytes,
      String dirPath) throws IOException {
    StorageDir dir = new StorageDir(tier, dirIndex, capacityBytes, dirPath);
    dir.initializeMeta();
    return dir;
  }