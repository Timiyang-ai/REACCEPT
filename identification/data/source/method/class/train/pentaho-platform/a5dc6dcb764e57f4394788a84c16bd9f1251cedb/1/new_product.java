public Job[] getJobs() throws SchedulerException {
    IScheduler scheduler = PentahoSystem.get( IScheduler.class, "IScheduler2", null ); //$NON-NLS-1$
    IPentahoSession session = PentahoSessionHolder.getSession();
    String principalName = session.getName();
    Boolean canAdminister = PentahoSystem.get( IAuthorizationPolicy.class ).isAllowed( ADMIN_PERM );

    return scheduler.getJobs( job -> {
      if ( canAdminister ) {
        return !IBlockoutManager.BLOCK_OUT_JOB_NAME.equals( job.getJobName() );
      }
      return principalName.equals( job.getUserName() );
    } ).toArray( new Job[0] );
  }