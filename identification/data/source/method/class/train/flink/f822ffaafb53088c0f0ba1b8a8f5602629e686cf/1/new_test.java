@Test
	public void testSchedulJob() {

		when(this.executionGraph.getNumberOfStages()).thenReturn(1);
		when(this.executionGraph.getStage(0)).thenReturn(this.stage1);
		when(this.executionGraph.getCurrentExecutionStage()).thenReturn(this.stage1);
		when(this.instanceManager.getMapOfAvailableInstanceTypes()).thenReturn(availableInstances);
		when(this.stage1.getExecutionGraph()).thenReturn(this.executionGraph);

		// correct walk through method
		final QueueScheduler toTest = new QueueScheduler(deploymentManager, this.instanceManager);
		try {
			toTest.schedulJob(this.executionGraph);
			final Deque<ExecutionGraph> jobQueue = Whitebox.getInternalState(toTest, "jobQueue");
			assertEquals("Job should be in list", true, jobQueue.contains(this.executionGraph));
			jobQueue.remove(this.executionGraph);

		} catch (SchedulingException e) {
			fail();
			e.printStackTrace();
		}

	}