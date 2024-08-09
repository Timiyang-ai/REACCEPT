public synchronized void submitRunTaskCommand(long jobId, int taskId, JobConfig jobConfig,
      Object taskArgs, long workerId) {
    RunTaskCommand runTaskCommand = new RunTaskCommand();
    runTaskCommand.setJobId(jobId);
    runTaskCommand.setTaskId(taskId);
    try {
      runTaskCommand.setJobConfig(SerializationUtils.serialize(jobConfig));
      runTaskCommand.setTaskArgs(SerializationUtils.serialize(taskArgs));
    } catch (IOException e) {
      // TODO(yupeng) better exception handling
      LOG.info("Failed to serialize the run task command:" + e);
      return;
    }
    JobCommand command = new JobCommand();
    command.setRunTaskCommand(runTaskCommand);
    if (!mWorkerIdToPendingCommands.containsKey(workerId)) {
      mWorkerIdToPendingCommands.put(workerId, Lists.<JobCommand>newArrayList());
    }
    mWorkerIdToPendingCommands.get(workerId).add(command);
  }