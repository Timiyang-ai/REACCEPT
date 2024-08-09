void release() {
    // Just use the default clock because we aren't actually doing anything that will use the clock.
    ofy().transact(new VoidWork() {
      @Override
      public void vrun() {
        // To release a lock, check that no one else has already obtained it and if not delete it.
        // If the lock in Datastore was different then this lock is gone already; this can happen
        // if release() is called around the expiration time and the lock expires underneath us.
        Lock loadedLock = ofy().load().type(Lock.class).id(lockId).now();
        if (Lock.this.equals(loadedLock)) {
          // Use noBackupOfy() so that we don't create a commit log entry for deleting the lock.
          ofy().deleteWithoutBackup().entity(Lock.this);
        }
      }});
  }