public MountInfo getMountInfo(long id) {
    try (LockResource r = new LockResource(mReadLock)) {
      for (Map.Entry<String, MountInfo> entry : mMountTable.entrySet()) {
        if (entry.getValue().getUfsId() == id) {
          return entry.getValue();
        }
      }
    }
    return null;
  }