public long lockBlock(long sessionId, long blockId, BlockLockType blockLockType) {
    ClientRWLock blockLock = getBlockLock(blockId);
    Lock lock;
    if (blockLockType == BlockLockType.READ) {
      lock = blockLock.readLock();
    } else {
      // Make sure the session isn't already holding the block lock.
      if (sessionHoldsLock(sessionId, blockId)) {
        throw new IllegalStateException(String
            .format("Session %s attempted to take a write lock on block %s, but the session already"
                + " holds a lock on the block", sessionId, blockId));
      }
      lock = blockLock.writeLock();
    }
    lock.lock();
    try {
      long lockId = LOCK_ID_GEN.getAndIncrement();
      synchronized (mSharedMapsLock) {
        mLockIdToRecordMap.put(lockId, new LockRecord(sessionId, blockId, lock));
        Set<Long> sessionLockIds = mSessionIdToLockIdsMap.get(sessionId);
        if (sessionLockIds == null) {
          mSessionIdToLockIdsMap.put(sessionId, Sets.newHashSet(lockId));
        } else {
          sessionLockIds.add(lockId);
        }
      }
      return lockId;
    } catch (RuntimeException e) {
      // If an unexpected exception occurs, we should release the lock to be conservative.
      unlock(lock, blockId);
      throw e;
    }
  }