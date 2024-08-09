public void clearPersistedFiles(List<Long> persistedFiles) {
    synchronized (mLock) {
      for (long persistedId : persistedFiles) {
        mPersistedFilesInfo.remove(persistedId);
      }
    }
  }