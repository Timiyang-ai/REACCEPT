public JobTracker findJobTracker( JobEntryCopy jobEntryCopy ) {
    if ( jobEntryCopy.getName() == null ) {
      return null;
    }

    lock.readLock().lock();
    try {
      ListIterator<JobTracker> it = jobTrackers.listIterator( jobTrackers.size() );
      while ( it.hasPrevious() ) {
        JobTracker tracker = it.previous();
        JobEntryResult result = tracker.getJobEntryResult();
        if ( result != null ) {
          if ( jobEntryCopy.getName().equals( result.getJobEntryName() )
            && jobEntryCopy.getNr() == result.getJobEntryNr() ) {
            return tracker;
          }
        }
      }
    } finally {
      lock.readLock().unlock();
    }
    return null;
  }