public synchronized void submitRunTaskCommand(long jobId, long taskId, JobConfig jobConfig,
      Object taskArgs, long workerId) {
    RunTaskCommand.Builder runTaskCommand = RunTaskCommand.newBuilder();
    runTaskCommand.setJobId(jobId);
    runTaskCommand.setTaskId(taskId);
    try {
      runTaskCommand.setJobConfig(ByteString.copyFrom(SerializationUtils.serialize(jobConfig)));
      if (taskArgs != null) {
        runTaskCommand.setTaskArgs(ByteString.copyFrom(SerializationUtils.serialize(taskArgs)));
      }
    } catch (IOException e) {
      // TODO(yupeng) better exception handling
      LOG.info("Failed to serialize the run task command:" + e);
      return;
    }
    JobCommand.Builder command = JobCommand.newBuilder();
    command.setRunTaskCommand(runTaskCommand);
    submit(workerId, command);
  }