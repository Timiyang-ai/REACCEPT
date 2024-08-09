public synchronized void cancel(long jobId) throws JobDoesNotExistException {
    if (!mIdToJobCoordinator.containsKey(jobId)) {
      throw new JobDoesNotExistException(ExceptionMessage.JOB_DOES_NOT_EXIST.getMessage(jobId));
    }
    JobCoordinator jobCoordinator = mIdToJobCoordinator.get(jobId);
    jobCoordinator.cancel();
  }