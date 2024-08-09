static Lock acquire(
      final String resourceName,
      @Nullable final String tld,
      final Duration leaseLength) {
    // It's important to use transactNew rather than transact, because a Lock can be used to control
    // access to resources like GCS that can't be transactionally rolled back. Therefore, the lock
    // must be definitively acquired before it is used, even when called inside another transaction.
    return ofy().transactNew(new Work<Lock>() {
      @Override
      public Lock run() {
        String lockId = makeLockId(resourceName, tld);
        DateTime now = ofy().getTransactionTime();

        // Checking if an unexpired lock still exists - if so, the lock can't be acquired.
        Lock lock = ofy().load().type(Lock.class).id(lockId).now();
        if (lock != null && !isAtOrAfter(now, lock.expirationTime)) {
          logger.infofmt(
              "Existing lock is still valid now %s (until %s) lock: %s",
              now,
              lock.expirationTime,
              lockId);
          return null;
        }

        if (lock != null) {
          logger.infofmt(
              "Existing lock is timed out now %s (was valid until %s) lock: %s",
              now,
              lock.expirationTime,
              lockId);
        }
        Lock newLock = create(
            resourceName,
            tld,
            now.plus(leaseLength));
        // Locks are not parented under an EntityGroupRoot (so as to avoid write contention) and
        // don't need to be backed up.
        ofy().saveWithoutBackup().entity(newLock);
        logger.infofmt(
            "acquire succeeded %s lock: %s",
            newLock,
            lockId);
        return newLock;
      }});
  }