public synchronized long run(JobConfig jobConfig)
      throws JobDoesNotExistException, ResourceExhaustedException {
    long jobId = mJobIdGenerator.getNewJobId();
    JobInfo jobInfo = new JobInfo(jobId, jobConfig, new Function<JobInfo, Void>() {
      @Override
      public Void apply(JobInfo jobInfo) {
        Status status = jobInfo.getStatus();
        mFinishedJobs.remove(jobInfo);
        if (status.isFinished()) {
          mFinishedJobs.add(jobInfo);
        }
        return null;
      }
    });
    if (mIdToJobCoordinator.size() == CAPACITY) {
      if (mFinishedJobs.isEmpty()) {
        // The job master is at full capacity and no job has finished.
        throw new ResourceExhaustedException("Job master is at full capacity");
      }
      // Check if the oldest finished job can be discarded.
      Iterator<JobInfo> jobIterator = mFinishedJobs.iterator();
      JobInfo oldestJob = jobIterator.next();
      if (CommonUtils.getCurrentMs() - oldestJob.getLastStatusChangeMs() < RETENTION_MS) {
        // do not evict the candidate job if it has finished recently
        throw new ResourceExhaustedException("Job master is at full capacity");
      }
      jobIterator.remove();
      mIdToJobCoordinator.remove(oldestJob.getId());
    }
    JobCoordinator jobCoordinator =
        JobCoordinator.create(mCommandManager, mUfsManager, getWorkerInfoList(), jobInfo);
    mIdToJobCoordinator.put(jobId, jobCoordinator);
    return jobId;
  }