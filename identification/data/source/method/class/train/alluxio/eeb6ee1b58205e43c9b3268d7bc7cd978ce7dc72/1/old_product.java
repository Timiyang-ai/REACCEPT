public void cleanupSessions() {
    for (long session: mSessions.getTimedOutSessions()) {
      mSessions.removeSession(session);
      mUnderFileSystemManager.cleanupSession(session);
    }
  }