public void cancel(long jobId) throws JobDoesNotExistException {
    JobCoordinator jobCoordinator = mTracker.getCoordinator(jobId);
    if (jobCoordinator == null) {
      throw new JobDoesNotExistException(ExceptionMessage.JOB_DOES_NOT_EXIST.getMessage(jobId));
    }
    jobCoordinator.cancel();
  }