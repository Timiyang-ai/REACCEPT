public long lockBlock(long userId, long blockId, BlockLockType blockLockType) {
    // hashing blockId into the range of [0, NUM_LOCKS-1]
    int hashValue = Math.abs(mHashFunc.hashLong(blockId).asInt()) % NUM_LOCKS;
    ClientRWLock blockLock = mLockArray[hashValue];
    Lock lock;
    if (blockLockType == BlockLockType.READ) {
      lock = blockLock.readLock();
    } else { // blockLockType == BlockLockType.WRITE
      lock = blockLock.writeLock();
    }
    lock.lock();
    long lockId = LOCK_ID_GEN.getAndIncrement();
    synchronized (mSharedMapsLock) {
      mLockIdToRecordMap.put(lockId, new LockRecord(userId, blockId, lock));
      Set<Long> userLockIds = mUserIdToLockIdsMap.get(userId);
      if (null == userLockIds) {
        mUserIdToLockIdsMap.put(userId, Sets.newHashSet(lockId));
      } else {
        userLockIds.add(lockId);
      }
    }
    return lockId;
  }