  @Test
  public void submitRunTaskCommand() throws Exception {
    long jobId = 0L;
    int taskId = 1;
    JobConfig jobConfig = new TestPlanConfig("/test");
    long workerId = 2L;
    List<Integer> args = Lists.newArrayList(1);
    mManager.submitRunTaskCommand(jobId, taskId, jobConfig, args, workerId);
    List<JobCommand> commands = mManager.pollAllPendingCommands(workerId);
    Assert.assertEquals(1, commands.size());
    JobCommand command = commands.get(0);
    Assert.assertEquals(jobId, command.getRunTaskCommand().getJobId());
    Assert.assertEquals(taskId, command.getRunTaskCommand().getTaskId());
    Assert.assertEquals(jobConfig,
        SerializationUtils.deserialize(command.getRunTaskCommand().getJobConfig().toByteArray()));
    Assert.assertEquals(args,
        SerializationUtils.deserialize(command.getRunTaskCommand().getTaskArgs().toByteArray()));
  }