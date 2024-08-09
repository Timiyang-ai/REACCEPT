public synchronized long run(JobConfig jobConfig)
      throws JobDoesNotExistException, ResourceExhaustedException {
    long jobId = getNewJobId();
    run(jobConfig, jobId);
    return jobId;
  }