private void setupSessionCleaner() {
    mSessionCleaner = new SessionCleaner(new SessionCleanupCallback() {
      /**
       * Cleans up after sessions, to prevent zombie sessions holding local resources.
       */
      @Override
      public void cleanupSessions() {
        for (long session : mSessions.getTimedOutSessions()) {
          mSessions.removeSession(session);
          mBlockStore.cleanupSession(session);
        }
      }
    });
  }