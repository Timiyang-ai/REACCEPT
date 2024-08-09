@Test
	public void testSchedulJob() {

		final InstanceType type = new InstanceType();
		InstanceTypeDescription desc = InstanceTypeDescriptionFactory.construct(type, new HardwareDescription(), 4);
		final HashMap<InstanceType, Integer> requiredInstanceTypes = new HashMap<InstanceType, Integer>();
		requiredInstanceTypes.put(type, 3);
		final HashMap<InstanceType, InstanceTypeDescription> availableInstances = new HashMap<InstanceType, InstanceTypeDescription>();
		availableInstances.put(type, desc);

		final DeploymentManager deploymentManager = new TestDeploymentManager();

		try {
			whenNew(HashMap.class).withNoArguments().thenReturn(requiredInstanceTypes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

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

		// not enough available Instances
		desc = InstanceTypeDescriptionFactory.construct(type, new HardwareDescription(), 2);
		availableInstances.put(type, desc);
		try {
			toTest.schedulJob(this.executionGraph);
			fail();

		} catch (SchedulingException e) {
			final Deque<ExecutionGraph> jobQueue = Whitebox.getInternalState(toTest, "jobQueue");
			assertEquals("Job should not be in list", false, jobQueue.contains(this.executionGraph));

		}
		// Instance unknown
		availableInstances.clear();
		try {
			toTest.schedulJob(this.executionGraph);
			fail();

		} catch (SchedulingException e) {
			Deque<ExecutionGraph> jobQueue = Whitebox.getInternalState(toTest, "jobQueue");
			assertEquals("Job should not be in list", false, jobQueue.contains(this.executionGraph));

		}
	}