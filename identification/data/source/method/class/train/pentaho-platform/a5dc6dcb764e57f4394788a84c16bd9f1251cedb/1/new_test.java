  private List<Job> getJobs() {
    List<Job> jobs = new ArrayList<>();
    for ( int i = 0; i < 10; i++ ) {
      jobs.add( mockJob( "testUser" + i, "testJobName" + i ) );
    }
    jobs.add( mockJob( "system", "BlockoutAction" ) );
    return jobs;
  }