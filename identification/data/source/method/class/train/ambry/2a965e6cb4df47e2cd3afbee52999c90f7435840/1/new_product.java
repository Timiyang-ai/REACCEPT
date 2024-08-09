boolean isDiskAvailable(DiskId disk) {
    DiskManager diskManager = diskToDiskManager.get(disk);
    return diskManager != null && !diskManager.areAllStoresDown();
  }