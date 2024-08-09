public void cleanupSession(long sessionId) {
    synchronized (mSharedMapsLock) {
      Set<Long> sessionLockIds = mSessionIdToLockIdsMap.get(sessionId);
      if (null == sessionLockIds) {
        return;
      }
      for (long lockId : sessionLockIds) {
        LockRecord record = mLockIdToRecordMap.get(lockId);
        if (null == record) {
          LOG.error(ExceptionMessage.LOCK_RECORD_NOT_FOUND_FOR_LOCK_ID.getMessage(lockId));
          continue;
        }
        Lock lock = record.lock();
        lock.unlock();
        mLockIdToRecordMap.remove(lockId);
      }
      mSessionIdToLockIdsMap.remove(sessionId);
    }
  }