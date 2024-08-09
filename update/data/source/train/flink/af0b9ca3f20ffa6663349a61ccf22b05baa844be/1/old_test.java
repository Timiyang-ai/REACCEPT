@Test
	public void testExecutionStateChanged() {

		final Environment ee = mock(Environment.class);

		final QueueScheduler localScheduler = mock(QueueScheduler.class);
		final ExecutionVertex executionVertex = mock(ExecutionVertex.class);
		final ExecutionGraph executionGraph = mock(ExecutionGraph.class);
		final AllocatedResource allocatedResource = mock(AllocatedResource.class);
		when(executionVertex.getExecutionGraph()).thenReturn(executionGraph);
		when(executionVertex.getAllocatedResource()).thenReturn(allocatedResource);

		final QueueExecutionListener toTest = new QueueExecutionListener(localScheduler, executionVertex);
		// State change to RUNNING
		when(executionGraph.getJobStatus()).thenReturn(InternalJobStatus.RUNNING);
		ExecutionState newExecutionState = ExecutionState.FINISHING;
		toTest.executionStateChanged(ee, newExecutionState, "");
		verify(localScheduler, times(0)).checkAndReleaseAllocatedResource(executionGraph, allocatedResource);
		verify(localScheduler, times(0)).removeJobFromSchedule(executionGraph);
		// Job finished
		newExecutionState = ExecutionState.FINISHED;
		when(executionGraph.getJobStatus()).thenReturn(InternalJobStatus.FINISHED);
		toTest.executionStateChanged(ee, newExecutionState, "");
		verify(localScheduler).checkAndReleaseAllocatedResource(executionGraph, allocatedResource);
		// execution state changed to fails, vertex should be rescheduled
		newExecutionState = ExecutionState.FAILED;
		when(executionVertex.hasRetriesLeft()).thenReturn(true);
		toTest.executionStateChanged(ee, newExecutionState, "");
		verify(executionVertex).setExecutionState(ExecutionState.SCHEDULED);

	}