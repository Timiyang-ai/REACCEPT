static Lock acquire(
      final Class<?> requester,
      final String resourceName,
      @Nullable final String tld,
      final Duration leaseLength) {
    // It's important to use transactNew rather than transact, because a Lock can be used to control
    // access to resources like GCS that can't be transactionally rolled back. Therefore, the lock
    // must be definitively acquired before it is used, even when called inside another transaction.
    return ofy().transactNew(new Work<Lock>() {
      @Override
      public Lock run() {
        Lock lock = ofy().load().type(Lock.class).id(makeLockId(resourceName, tld)).now();
        if (lock == null || isAtOrAfter(ofy().getTransactionTime(), lock.expirationTime)) {
          String requesterName = (requester == null) ? "" : requester.getCanonicalName();
          if (!getFirst(nullToEmpty((lock == null) ? null : lock.queue), requesterName)
              .equals(requesterName)) {
            // Another class is at the top of the queue; we can't acquire the lock.
            return null;
          }
          Lock newLock = create(
              resourceName,
              tld,
              ofy().getTransactionTime().plus(leaseLength),
              newLinkedHashSet((lock == null)
                  ? ImmutableList.<String>of() : skip(lock.queue, 1)));
          // Locks are not parented under an EntityGroupRoot (so as to avoid write contention) and
          // don't need to be backed up.
          ofy().saveWithoutBackup().entity(newLock);
          return newLock;
        }
        return null;
      }});
  }