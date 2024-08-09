public void clearPersistedFiles(List<Long> persistedFiles) {
    synchronized (mLock) {
      mPersistedFiles.removeAll(persistedFiles);
    }
  }