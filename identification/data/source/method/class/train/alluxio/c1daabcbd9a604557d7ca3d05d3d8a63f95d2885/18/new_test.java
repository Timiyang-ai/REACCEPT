  @Test
  public void run() throws Exception {
    PlanCoordinator coordinator = PowerMockito.mock(PlanCoordinator.class);
    PowerMockito.mockStatic(PlanCoordinator.class);
    when(
        PlanCoordinator.create(any(CommandManager.class),
            any(JobServerContext.class), anyList(), anyLong(), any(JobConfig.class), any(null)))
        .thenReturn(coordinator);
    TestPlanConfig jobConfig = new TestPlanConfig("/test");
    for (long i = 0; i < TEST_JOB_MASTER_JOB_CAPACITY; i++) {
      mJobMaster.run(jobConfig);
    }
    Assert.assertEquals(TEST_JOB_MASTER_JOB_CAPACITY, mJobMaster.list().size());
  }