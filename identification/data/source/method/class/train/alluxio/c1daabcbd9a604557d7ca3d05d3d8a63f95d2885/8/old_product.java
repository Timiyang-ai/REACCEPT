public synchronized long run(JobConfig jobConfig)
      throws JobDoesNotExistException, ResourceExhaustedException {
    // This RPC service implementation triggers another RPC.
    // Run the implementation under forked context to avoid interference.
    // Then restore the current context at the end.
    Context forkedCtx = Context.current().fork();
    Context prevCtx = forkedCtx.attach();
    try {
      long jobId = getNewJobId();
      if (jobConfig instanceof PlanConfig) {
        mPlanTracker.run((PlanConfig) jobConfig, mCommandManager, mJobServerContext,
            getWorkerInfoList(), jobId);
        return jobId;
      }
      throw new JobDoesNotExistException(
          ExceptionMessage.JOB_DEFINITION_DOES_NOT_EXIST.getMessage(jobConfig.getName()));
    } finally {
      forkedCtx.detach(prevCtx);
    }
  }