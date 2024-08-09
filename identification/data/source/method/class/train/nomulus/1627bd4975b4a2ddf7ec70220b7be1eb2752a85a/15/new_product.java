public static Optional<Lock> acquire(
      final String resourceName,
      @Nullable final String tld,
      final Duration leaseLength,
      final RequestStatusChecker requestStatusChecker) {
    // It's important to use transactNew rather than transact, because a Lock can be used to control
    // access to resources like GCS that can't be transactionally rolled back. Therefore, the lock
    // must be definitively acquired before it is used, even when called inside another transaction.
    return Optional.fromNullable(ofy().transactNew(new Work<Lock>() {
      @Override
      public Lock run() {
        String lockId = makeLockId(resourceName, tld);
        DateTime now = ofy().getTransactionTime();

        // Checking if an unexpired lock still exists - if so, the lock can't be acquired.
        Lock lock = ofy().load().type(Lock.class).id(lockId).now();
        if (lock != null) {
          logger.infofmt(
              "Loaded existing lock: %s for request: %s", lock.lockId, lock.requestLogId);
        }
        // TODO(b/63982642): remove check on requestLogId being null once migration is done
        // Until then we assume missing requestLogId means the app is still running (since we have
        // no information to the contrary)
        if (lock != null
            && !isAtOrAfter(now, lock.expirationTime)
            && (lock.requestLogId == null || requestStatusChecker.isRunning(lock.requestLogId))) {
          logger.infofmt(
              "Existing lock by request %s is still valid now %s (until %s) lock: %s",
              lock.requestLogId,
              now,
              lock.expirationTime,
              lockId);
          return null;
        }

        if (lock != null) {
          logger.infofmt(
              "Existing lock by request %s is timed out now %s (was valid until %s) lock: %s",
              lock.requestLogId,
              now,
              lock.expirationTime,
              lockId);
        }
        Lock newLock = create(
            resourceName,
            tld,
            requestStatusChecker.getLogId(),
            now.plus(leaseLength));
        // Locks are not parented under an EntityGroupRoot (so as to avoid write contention) and
        // don't need to be backed up.
        ofy().saveWithoutBackup().entity(newLock);
        logger.infofmt(
            "acquire succeeded %s lock: %s",
            newLock,
            lockId);
        return newLock;
      }}));
  }