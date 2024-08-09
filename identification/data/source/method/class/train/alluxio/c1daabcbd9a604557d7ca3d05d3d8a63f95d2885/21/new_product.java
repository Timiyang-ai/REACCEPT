public synchronized long run(JobConfig jobConfig)
      throws JobDoesNotExistException, ResourceExhaustedException {
    // This RPC service implementation triggers another RPC.
    // Run the implementation under forked context to avoid interference.
    // Then restore the current context at the end.
    Context forkedCtx = Context.current().fork();
    Context prevCtx = forkedCtx.attach();
    try {
      if (mIdToJobCoordinator.size() == mCapacity) {
        if (mFinishedJobs.isEmpty()) {
          // The job master is at full capacity and no job has finished.
          throw new ResourceExhaustedException(
              ExceptionMessage.JOB_MASTER_FULL_CAPACITY.getMessage(mCapacity));
        }
        // Discard old jobs that have completion time beyond retention policy
        Iterator<JobInfo> jobIterator = mFinishedJobs.iterator();
        // Used to denote whether space could be reserved for the new job
        // It's 'true' if job master is at full capacity
        boolean isFull = true;
        while (jobIterator.hasNext()) {
          JobInfo oldestJob = jobIterator.next();
          long completedBeforeMs = CommonUtils.getCurrentMs() - oldestJob.getLastStatusChangeMs();
          if (completedBeforeMs < RETENTION_MS) {
            // mFinishedJobs is sorted. Can't iterate to a job within retention policy
            break;
          }
          jobIterator.remove();
          mIdToJobCoordinator.remove(oldestJob.getId());
          isFull = false;
        }
        if (isFull) {
          throw new ResourceExhaustedException(
              ExceptionMessage.JOB_MASTER_FULL_CAPACITY.getMessage(mCapacity));
        }
      }
      long jobId = mJobIdGenerator.getNewJobId();
      JobCoordinator jobCoordinator = JobCoordinator.create(mCommandManager, mJobServerContext,
          getWorkerInfoList(), jobId, jobConfig, (jobInfo) -> {
            Status status = jobInfo.getStatus();
            mFinishedJobs.remove(jobInfo);
            if (status.isFinished()) {
              mFinishedJobs.add(jobInfo);
            }
            return null;
          });
      mIdToJobCoordinator.put(jobId, jobCoordinator);
      return jobId;
    } finally {
      forkedCtx.detach(prevCtx);
    }
  }