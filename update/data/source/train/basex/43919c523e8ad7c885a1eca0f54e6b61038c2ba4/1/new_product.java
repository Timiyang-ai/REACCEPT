@Override
  public void downgrade(final StringList write) {
    final Long thread = Thread.currentThread().getId();
    if(null == write)
      throw new IllegalMonitorStateException("Cannot downgrade to global write lock.");
    write.sort(true).unique();

    // Fetch current locking status
    final StringList writeObjects = writeLocked.remove(thread);
    final StringList readObjects = readLocked.remove(thread);
    final StringList newWriteObjects = new StringList();
    StringList newReadObjects = new StringList();
    if(null != readObjects) newReadObjects.add(readObjects);

    if(null != writeObjects) {
      if(!writeObjects.containsAll(write)) throw new IllegalMonitorStateException(
          "Cannot downgrade write lock that has not been acquired.");

      // Perform downgrades
      for(final String object : writeObjects) {
        if(write.contains(object)) {
          newWriteObjects.add(object);
        } else {
          final ReentrantReadWriteLock lock = getOrCreateLock(object);
          assert 1 == lock.getWriteHoldCount() : "Unexpected write lock count: "
              + lock.getWriteHoldCount();
          lock.readLock().lock();
          newReadObjects.add(object);
          lock.writeLock().unlock();
        }
      }
    }

    // Downgrade from global write lock to global read lock
    if(writeAll.writeLock().isHeldByCurrentThread()) {
      for (final String object : write) {
        getOrCreateLock(object).writeLock().lock();
        setLockUsed(object);
      }
      newWriteObjects.add(write);
      // Release all local read locks as we're fetching a global one
      for(final String object : readObjects) {
        getOrCreateLock(object).readLock().unlock();
        unsetLockIfUnused(object);
      }
      newReadObjects = null;
      readLocked.remove(thread);
      writeAll.readLock().lock();
      writeAll.writeLock().unlock();

      synchronized(globalLock) {
        if(!write.isEmpty())
          localWriters++;
        globalReaders++;
        globalLock.notifyAll();
      }
    }

    // Downgrade from local write lock to no write locks
    synchronized(globalLock) {
      if(write.isEmpty()) {
        localWriters--;
        globalLock.notifyAll();
      }
    }

    // Write back new locking lists
    writeLocked.put(thread, newWriteObjects);
    if (null != newReadObjects)
      readLocked.put(thread, newReadObjects);
  }