@Test
	@SuppressWarnings("unchecked")
	public void testTriggerStackTraceSample() throws Exception {
		CompletableFuture<StackTraceSample> sampleFuture = new CompletableFuture<>();

		StackTraceSampleCoordinator sampleCoordinator = Mockito.mock(StackTraceSampleCoordinator.class);
		Mockito.when(sampleCoordinator.triggerStackTraceSample(
				Matchers.any(ExecutionVertex[].class),
				Matchers.anyInt(),
				Matchers.any(Time.class),
				Matchers.anyInt())).thenReturn(sampleFuture);

		ExecutionGraph graph = Mockito.mock(ExecutionGraph.class);
		Mockito.when(graph.getState()).thenReturn(JobStatus.RUNNING);

		// Same Thread execution context
		Mockito.when(graph.getFutureExecutor()).thenReturn(new Executor() {

			@Override
			public void execute(Runnable runnable) {
				runnable.run();
			}
		});

		ExecutionVertex[] taskVertices = new ExecutionVertex[4];

		ExecutionJobVertex jobVertex = Mockito.mock(ExecutionJobVertex.class);
		Mockito.when(jobVertex.getJobId()).thenReturn(new JobID());
		Mockito.when(jobVertex.getJobVertexId()).thenReturn(new JobVertexID());
		Mockito.when(jobVertex.getGraph()).thenReturn(graph);
		Mockito.when(jobVertex.getTaskVertices()).thenReturn(taskVertices);

		taskVertices[0] = mockExecutionVertex(jobVertex, 0);
		taskVertices[1] = mockExecutionVertex(jobVertex, 1);
		taskVertices[2] = mockExecutionVertex(jobVertex, 2);
		taskVertices[3] = mockExecutionVertex(jobVertex, 3);

		int numSamples = 100;
		Time delayBetweenSamples = Time.milliseconds(100L);

		BackPressureStatsTracker tracker = new BackPressureStatsTracker(
				sampleCoordinator, 9999, numSamples, Integer.MAX_VALUE, delayBetweenSamples);

		// getOperatorBackPressureStats triggers stack trace sampling
		Assert.assertFalse(tracker.getOperatorBackPressureStats(jobVertex).isPresent());

		Mockito.verify(sampleCoordinator, Mockito.times(1)).triggerStackTraceSample(
				Matchers.eq(taskVertices),
				Matchers.eq(numSamples),
				Matchers.eq(delayBetweenSamples),
				Matchers.eq(BackPressureStatsTracker.MAX_STACK_TRACE_DEPTH));

		// Request back pressure stats again. This should not trigger another sample request
		Assert.assertTrue(!tracker.getOperatorBackPressureStats(jobVertex).isPresent());

		Mockito.verify(sampleCoordinator, Mockito.times(1)).triggerStackTraceSample(
				Matchers.eq(taskVertices),
				Matchers.eq(numSamples),
				Matchers.eq(delayBetweenSamples),
				Matchers.eq(BackPressureStatsTracker.MAX_STACK_TRACE_DEPTH));

		Assert.assertTrue(!tracker.getOperatorBackPressureStats(jobVertex).isPresent());

		// Complete the future
		Map<ExecutionAttemptID, List<StackTraceElement[]>> traces = new HashMap<>();
		for (ExecutionVertex vertex : taskVertices) {
			List<StackTraceElement[]> taskTraces = new ArrayList<>();

			for (int i = 0; i < taskVertices.length; i++) {
				// Traces until sub task index are back pressured
				taskTraces.add(createStackTrace(i <= vertex.getParallelSubtaskIndex()));
			}

			traces.put(vertex.getCurrentExecutionAttempt().getAttemptId(), taskTraces);
		}

		int sampleId = 1231;
		int endTime = 841;

		StackTraceSample sample = new StackTraceSample(
				sampleId,
				0,
				endTime,
				traces);

		// Succeed the promise
		sampleFuture.complete(sample);

		Assert.assertTrue(tracker.getOperatorBackPressureStats(jobVertex).isPresent());

		OperatorBackPressureStats stats = tracker.getOperatorBackPressureStats(jobVertex).get();

		// Verify the stats
		Assert.assertEquals(sampleId, stats.getSampleId());
		Assert.assertEquals(endTime, stats.getEndTimestamp());
		Assert.assertEquals(taskVertices.length, stats.getNumberOfSubTasks());

		for (int i = 0; i < taskVertices.length; i++) {
			double ratio = stats.getBackPressureRatio(i);
			// Traces until sub task index are back pressured
			Assert.assertEquals((i + 1) / ((double) 4), ratio, 0.0);
		}
	}