public Job[] getJobs() throws SchedulerException {
    IScheduler scheduler = PentahoSystem.get( IScheduler.class, "IScheduler2", null ); //$NON-NLS-1$
    return scheduler.getJobs( null ).toArray( new Job[0] );
  }