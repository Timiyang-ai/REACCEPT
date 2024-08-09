@Test
	@SuppressWarnings("unchecked")
	public void testTriggerStackTraceSample() throws Exception {
		CompletableFuture<StackTraceSample> sampleFuture = new FlinkCompletableFuture<>();

		StackTraceSampleCoordinator sampleCoordinator = mock(StackTraceSampleCoordinator.class);
		when(sampleCoordinator.triggerStackTraceSample(
				any(ExecutionVertex[].class),
				anyInt(),
				any(Time.class),
				anyInt())).thenReturn(sampleFuture);

		ExecutionGraph graph = mock(ExecutionGraph.class);
		when(graph.getState()).thenReturn(JobStatus.RUNNING);

		// Same Thread execution context
		when(graph.getExecutor()).thenReturn(new Executor() {

			@Override
			public void execute(Runnable runnable) {
				runnable.run();
			}
		});

		ExecutionVertex[] taskVertices = new ExecutionVertex[4];

		ExecutionJobVertex jobVertex = mock(ExecutionJobVertex.class);
		when(jobVertex.getJobId()).thenReturn(new JobID());
		when(jobVertex.getJobVertexId()).thenReturn(new JobVertexID());
		when(jobVertex.getGraph()).thenReturn(graph);
		when(jobVertex.getTaskVertices()).thenReturn(taskVertices);

		taskVertices[0] = mockExecutionVertex(jobVertex, 0);
		taskVertices[1] = mockExecutionVertex(jobVertex, 1);
		taskVertices[2] = mockExecutionVertex(jobVertex, 2);
		taskVertices[3] = mockExecutionVertex(jobVertex, 3);

		int numSamples = 100;
		Time delayBetweenSamples = Time.milliseconds(100L);

		BackPressureStatsTracker tracker = new BackPressureStatsTracker(
				sampleCoordinator, 9999, numSamples, delayBetweenSamples);

		// Trigger
		assertTrue("Failed to trigger", tracker.triggerStackTraceSample(jobVertex));

		verify(sampleCoordinator).triggerStackTraceSample(
				eq(taskVertices),
				eq(numSamples),
				eq(delayBetweenSamples),
				eq(BackPressureStatsTracker.MAX_STACK_TRACE_DEPTH));

		// Trigger again for pending request, should not fire
		assertFalse("Unexpected trigger", tracker.triggerStackTraceSample(jobVertex));

		assertTrue(tracker.getOperatorBackPressureStats(jobVertex).isEmpty());

		verify(sampleCoordinator).triggerStackTraceSample(
				eq(taskVertices),
				eq(numSamples),
				eq(delayBetweenSamples),
				eq(BackPressureStatsTracker.MAX_STACK_TRACE_DEPTH));

		assertTrue(tracker.getOperatorBackPressureStats(jobVertex).isEmpty());

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

		assertTrue(tracker.getOperatorBackPressureStats(jobVertex).isDefined());

		OperatorBackPressureStats stats = tracker.getOperatorBackPressureStats(jobVertex).get();

		// Verify the stats
		assertEquals(sampleId, stats.getSampleId());
		assertEquals(endTime, stats.getEndTimestamp());
		assertEquals(taskVertices.length, stats.getNumberOfSubTasks());
		
		for (int i = 0; i < taskVertices.length; i++) {
			double ratio = stats.getBackPressureRatio(i);
			// Traces until sub task index are back pressured
			assertEquals((i + 1) / ((double) 4), ratio, 0.0);
		}
	}