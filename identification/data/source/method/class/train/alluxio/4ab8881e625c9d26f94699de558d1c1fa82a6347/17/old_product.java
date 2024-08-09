public Optional<Long> lockBlock(long userId, long blockId, BlockLockType blockLockType) {
    // TODO: generate real hashValue on blockID.
    int hashValue = (int) (blockId % (long) NUM_LOCKS);
    ClientRWLock blockLock = mLockArray.get(hashValue);
    Lock lock = null;
    if (blockLockType == BlockLockType.READ) {
      lock = blockLock.readLock();
    } else if (blockLockType == BlockLockType.WRITE) {
      lock = blockLock.writeLock();
    }
    lock.lock();
    if (mMetaManager.hasBlockMeta(blockId)) {
      lock.unlock();
      return Optional.absent();
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
    return Optional.of(lockId);
  }