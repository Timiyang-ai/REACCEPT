public synchronized void submitCancelTaskCommand(long jobId, int taskId, long workerId) {
    CancelTaskCommand cancelTaskCommand = new CancelTaskCommand();
    cancelTaskCommand.setJobId(jobId);
    cancelTaskCommand.setTaskId(taskId);
    JobCommand command = new JobCommand();
    command.setCancelTaskCommand(cancelTaskCommand);
    if (!mWorkerIdToPendingCommands.containsKey(workerId)) {
      mWorkerIdToPendingCommands.put(workerId, Lists.<JobCommand>newArrayList());
    }
    mWorkerIdToPendingCommands.get(workerId).add(command);
  }