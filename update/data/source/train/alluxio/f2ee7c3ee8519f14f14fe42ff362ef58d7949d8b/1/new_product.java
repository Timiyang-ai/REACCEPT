public void clearPersistedFiles(List<Long> persistedFiles) {
    synchronized (mLock) {
      for (long persistedId : persistedFiles) {
        mPersistedUfsFingerprints.remove(persistedId);
      }
    }
  }