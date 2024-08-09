public synchronized void submitCancelTaskCommand(long jobId, long taskId, long workerId) {
    CancelTaskCommand.Builder cancelTaskCommand = CancelTaskCommand.newBuilder();
    cancelTaskCommand.setJobId(jobId);
    cancelTaskCommand.setTaskId(taskId);
    JobCommand.Builder command = JobCommand.newBuilder();
    command.setCancelTaskCommand(cancelTaskCommand);
    submit(workerId, command);
  }