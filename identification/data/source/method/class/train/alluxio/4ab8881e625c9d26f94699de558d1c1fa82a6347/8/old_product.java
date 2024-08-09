public long lockBlock(long userId, long blockId, BlockLockType blockLockType) throws IOException {
    // hashing blockId into the range of [0, NUM_LOCKS-1]
    int index = blockHashIndex(blockId);
    ClientRWLock blockLock = mLockArray[index];
    Lock lock;
    if (blockLockType == BlockLockType.READ) {
      lock = blockLock.readLock();
    } else { // blockLockType == BlockLockType.WRITE
      lock = blockLock.writeLock();
    }
    lock.lock();
    if (!mMetaManager.hasBlockMeta(blockId)) {
      lock.unlock();
      throw new IOException("Failed to lockBlock: no blockId " + blockId + " found");
    }
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