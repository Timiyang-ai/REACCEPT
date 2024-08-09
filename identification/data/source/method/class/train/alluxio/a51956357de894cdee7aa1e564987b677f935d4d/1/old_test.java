  @Test
  public void cancel() throws Exception {
    PlanCoordinator coordinator = mock(PlanCoordinator.class);
    long jobId = 1L;
    PlanTracker tracker = new PlanTracker(10, 0, -1, mock(WorkflowTracker.class));
    ((Map<Long, PlanCoordinator>) Whitebox.getInternalState(tracker, "mCoordinators"))
        .put(jobId, coordinator);
    Whitebox.setInternalState(mJobMaster, "mPlanTracker", tracker);
    mJobMaster.cancel(jobId);
    verify(coordinator).cancel();
  }