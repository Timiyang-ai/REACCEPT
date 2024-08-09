public synchronized void submitCancelTaskCommand(long jobId, int taskId, long workerId) {
    CancelTaskCommand.Builder cancelTaskCommand = CancelTaskCommand.newBuilder();
    cancelTaskCommand.setJobId(jobId);
    cancelTaskCommand.setTaskId(taskId);
    JobCommand.Builder command = JobCommand.newBuilder();
    command.setCancelTaskCommand(cancelTaskCommand);
    if (!mWorkerIdToPendingCommands.containsKey(workerId)) {
      mWorkerIdToPendingCommands.put(workerId, Lists.<JobCommand>newArrayList());
    }
    mWorkerIdToPendingCommands.get(workerId).add(command.build());
  }