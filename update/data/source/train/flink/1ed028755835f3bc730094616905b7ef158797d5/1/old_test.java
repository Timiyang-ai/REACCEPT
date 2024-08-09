@Test
	public void testResourceAllocated() throws Exception {

		final DeploymentManager deploymentManager = new TestDeploymentManager();

		final QueueScheduler toTest = spy(new QueueScheduler(deploymentManager, this.instanceManager));
		final JobID jobid = mock(JobID.class);
		final AllocatedResource resource = mock(AllocatedResource.class);
		final InstanceType instanceType = new InstanceType();
		InstanceConnectionInfo instanceConnectionInfo = mock(InstanceConnectionInfo.class);
		when(instanceConnectionInfo.toString()).thenReturn("");
		LocalInstance instance = spy(new LocalInstance(instanceType, instanceConnectionInfo, null, null, null));

		// given resource is null
		toTest.resourceAllocated(null, null);
		verify(this.loggerMock).error(Matchers.anyString());

		// jobs have have been canceled
		final Method methodToMock = MemberMatcher.method(QueueScheduler.class, JobID.class);
		PowerMockito.when(toTest, methodToMock).withArguments(Matchers.any(JobID.class)).thenReturn(null);
		when(resource.getInstance()).thenReturn(instance);

		toTest.resourceAllocated(jobid, resource);
		try {
			verify(this.instanceManager).releaseAllocatedResource(Matchers.any(JobID.class),
				Matchers.any(Configuration.class), Matchers.any(AllocatedResource.class));
		} catch (InstanceException e1) {
			e1.printStackTrace();
		}

		// vertex resource is null
		PowerMockito.when(toTest, methodToMock).withArguments(Matchers.any(JobID.class))
			.thenReturn(this.executionGraph);
		when(this.graphIterator.next()).thenReturn(this.vertex1);
		when(this.graphIterator.hasNext()).thenReturn(true, true, true, true, false);
		when(this.graphIterator2.next()).thenReturn(this.vertex1);
		when(this.graphIterator2.hasNext()).thenReturn(true, true, true, true, false);
		when(this.vertex1.getExecutionState()).thenReturn(ExecutionState.SCHEDULED);
		try {
			whenNew(ExecutionGraphIterator.class).withArguments(Matchers.any(ExecutionGraph.class),
				Matchers.anyBoolean()).thenReturn(this.graphIterator);
			whenNew(ExecutionGraphIterator.class).withArguments(Matchers.any(ExecutionGraph.class), Matchers.anyInt(),
				Matchers.anyBoolean(), Matchers.anyBoolean()).thenReturn(this.graphIterator2);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		when(this.executionGraph.getJobID()).thenReturn(jobid);
		Deque<ExecutionGraph> jobQueue = Whitebox.getInternalState(toTest, "jobQueue");
		jobQueue.add(this.executionGraph);
		Whitebox.setInternalState(toTest, "jobQueue", jobQueue);
		when(this.vertex1.getAllocatedResource()).thenReturn(null);
		when(resource.getInstance()).thenReturn(instance);

		toTest.resourceAllocated(jobid, resource);
		verify(this.loggerMock).warn(Matchers.anyString());

		// correct walk through method
		when(this.graphIterator2.hasNext()).thenReturn(true, true, true, true, false);
		when(this.graphIterator.hasNext()).thenReturn(true, true, true, true, false);
		when(this.vertex1.getAllocatedResource()).thenReturn(resource);
		when(resource.getInstanceType()).thenReturn(instanceType);

		toTest.resourceAllocated(jobid, resource);
		verify(this.vertex1, times(4)).setExecutionState(ExecutionState.READY);

	}