public void cancel(long jobId) throws JobDoesNotExistException {
    JobCoordinator jobCoordinator = mIdToJobCoordinator.get(jobId);
    if (jobCoordinator == null) {
      throw new JobDoesNotExistException(ExceptionMessage.JOB_DOES_NOT_EXIST.getMessage(jobId));
    }
    jobCoordinator.cancel();
  }