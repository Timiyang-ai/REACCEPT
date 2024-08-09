public void cleanupSession(long sessionId) {
    try (LockResource r = new LockResource(mSharedMapsLock.writeLock())) {
      Set<Long> sessionLockIds = mSessionIdToLockIdsMap.get(sessionId);
      if (sessionLockIds == null) {
        return;
      }
      for (long lockId : sessionLockIds) {
        LockRecord record = mLockIdToRecordMap.get(lockId);
        if (record == null) {
          LOG.error(ExceptionMessage.LOCK_RECORD_NOT_FOUND_FOR_LOCK_ID.getMessage(lockId));
          continue;
        }
        Lock lock = record.getLock();
        unlock(lock, record.getBlockId());
        mLockIdToRecordMap.remove(lockId);
      }
      mSessionIdToLockIdsMap.remove(sessionId);
    }
  }