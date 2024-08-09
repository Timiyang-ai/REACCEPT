public static Optional<Lock> acquire(
      final String resourceName,
      @Nullable final String tld,
      final Duration leaseLength,
      final RequestStatusChecker requestStatusChecker) {
    String lockId = makeLockId(resourceName, tld);
    // It's important to use transactNew rather than transact, because a Lock can be used to control
    // access to resources like GCS that can't be transactionally rolled back. Therefore, the lock
    // must be definitively acquired before it is used, even when called inside another transaction.
    AcquireResult acquireResult =
        ofy()
            .transactNew(
                () -> {
                  DateTime now = ofy().getTransactionTime();

                  // Checking if an unexpired lock still exists - if so, the lock can't be acquired.
                  Lock lock = ofy().load().type(Lock.class).id(lockId).now();
                  if (lock != null) {
                    logger.atInfo().log(
                        "Loaded existing lock: %s for request: %s", lock.lockId, lock.requestLogId);
                  }
                  LockState lockState;
                  if (lock == null) {
                    lockState = LockState.FREE;
                  } else if (isAtOrAfter(now, lock.expirationTime)) {
                    lockState = LockState.TIMED_OUT;
                  } else if (!requestStatusChecker.isRunning(lock.requestLogId)) {
                    lockState = LockState.OWNER_DIED;
                  } else {
                    lockState = LockState.IN_USE;
                    return AcquireResult.create(now, lock, null, lockState);
                  }

                  Lock newLock =
                      create(resourceName, tld, requestStatusChecker.getLogId(), now, leaseLength);
                  // Locks are not parented under an EntityGroupRoot (so as to avoid write
                  // contention) and
                  // don't need to be backed up.
                  ofy().saveWithoutBackup().entity(newLock);
                  return AcquireResult.create(now, lock, newLock, lockState);
                });

    logAcquireResult(acquireResult);
    lockMetrics.recordAcquire(resourceName, tld, acquireResult.lockState());
    return Optional.ofNullable(acquireResult.newLock());
  }