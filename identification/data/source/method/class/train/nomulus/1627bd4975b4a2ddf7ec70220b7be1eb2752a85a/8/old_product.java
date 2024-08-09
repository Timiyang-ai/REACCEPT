public void release() {
    // Just use the default clock because we aren't actually doing anything that will use the clock.
    ofy()
        .transact(
            () -> {
              // To release a lock, check that no one else has already obtained it and if not
              // delete it. If the lock in Datastore was different then this lock is gone already;
              // this can happen if release() is called around the expiration time and the lock
              // expires underneath us.
              Lock loadedLock = ofy().load().type(Lock.class).id(lockId).now();
              if (Lock.this.equals(loadedLock)) {
                // Use noBackupOfy() so that we don't create a commit log entry for deleting the
                // lock.
                logger.atInfo().log("Deleting lock: %s", lockId);
                ofy().deleteWithoutBackup().entity(Lock.this);
                lockMetrics.recordRelease(
                    resourceName, tld, new Duration(acquiredTime, ofy().getTransactionTime()));
              } else {
                logger.atSevere().log(
                    "The lock we acquired was transferred to someone else before we"
                        + " released it! Did action take longer than lease length?"
                        + " Our lock: %s, current lock: %s",
                    Lock.this, loadedLock);
                logger.atInfo().log(
                    "Not deleting lock: %s - someone else has it: %s", lockId, loadedLock);
              }
            });
  }