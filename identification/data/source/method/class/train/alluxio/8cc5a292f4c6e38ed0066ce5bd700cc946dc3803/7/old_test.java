  @Test
  public void submitCancelTaskCommand() {
    long jobId = 0L;
    int taskId = 1;
    long workerId = 2L;
    mManager.submitCancelTaskCommand(jobId, taskId, workerId);
    List<JobCommand> commands = mManager.pollAllPendingCommands(workerId);
    Assert.assertEquals(1, commands.size());
    JobCommand command = commands.get(0);
    Assert.assertEquals(jobId, command.getCancelTaskCommand().getJobId());
    Assert.assertEquals(taskId, command.getCancelTaskCommand().getTaskId());
  }