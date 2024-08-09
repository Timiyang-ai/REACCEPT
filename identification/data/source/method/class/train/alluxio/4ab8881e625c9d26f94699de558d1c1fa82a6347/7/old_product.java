public long lockBlock(long sessionId, long blockId, BlockLockType blockLockType) {
    // hashing blockId into the range of [0, NUM_LOCKS-1]
    int index = blockHashIndex(blockId);
    ClientRWLock blockLock = mLockArray[index];
    Lock lock;
    if (blockLockType == BlockLockType.READ) {
      lock = blockLock.readLock();
    } else {
      lock = blockLock.writeLock();
    }
    lock.lock();
    long lockId = LOCK_ID_GEN.getAndIncrement();
    synchronized (mSharedMapsLock) {
      mLockIdToRecordMap.put(lockId, new LockRecord(sessionId, blockId, lock));
      Set<Long> sessionLockIds = mSessionIdToLockIdsMap.get(sessionId);
      if (null == sessionLockIds) {
        mSessionIdToLockIdsMap.put(sessionId, Sets.newHashSet(lockId));
      } else {
        sessionLockIds.add(lockId);
      }
    }
    return lockId;
  }