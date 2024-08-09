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

    // The block lock may be busy, wait up to five seconds to obtain it.
    boolean success;
    try {
      success = lock.tryLock(LOCK_ACQUIRE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    } catch (InterruptedException ie) {
      // The UserLock implementation does not throw this exception, something is wrong if it happens
      LOG.error("Interrupted exception in tryLock, this should not occur!");
      throw new IOException(ie.getMessage(), ie.getCause());
    }
    if (!success) {
      String errMsg = "5s timeout when attempting lockBlock: " + blockId + " for user: " + userId;
      LOG.error(errMsg);
      throw new IOException(errMsg);
    }
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