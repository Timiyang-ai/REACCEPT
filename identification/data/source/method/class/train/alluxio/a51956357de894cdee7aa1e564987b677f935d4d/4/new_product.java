public void cancel(long jobId) throws JobDoesNotExistException {
    PlanCoordinator planCoordinator = mPlanTracker.getCoordinator(jobId);
    if (planCoordinator == null) {
      if (!mWorkflowTracker.cancel(jobId)) {
        throw new JobDoesNotExistException(ExceptionMessage.JOB_DOES_NOT_EXIST.getMessage(jobId));
      }
      return;
    }
    planCoordinator.cancel();
  }