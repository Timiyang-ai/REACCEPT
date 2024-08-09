@Test
	@SuppressWarnings("unchecked")
	public void testTriggerStackTraceSample() throws Exception {
		Promise<StackTraceSample> samplePromise = new Promise.DefaultPromise<>();

		StackTraceSampleCoordinator sampleCoordinator = mock(StackTraceSampleCoordinator.class);
		when(sampleCoordinator.triggerStackTraceSample(
				any(ExecutionVertex[].class),
				anyInt(),
				any(FiniteDuration.class),
				anyInt())).thenReturn(samplePromise.future());

		ExecutionGraph graph = mock(ExecutionGraph.class);

		// Same Thread execution context
		when(graph.getExecutionContext()).thenReturn(new ExecutionContext() {

			@Override
			public void execute(Runnable runnable) {
				runnable.run();
			}

			@Override
			public void reportFailure(Throwable t) {
				fail();
			}

			@Override
			public ExecutionContext prepare() {
				return this;
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
		FiniteDuration delayBetweenSamples = new FiniteDuration(100, TimeUnit.MILLISECONDS);

		BackPressureStatsTracker tracker = new BackPressureStatsTracker(
				sampleCoordinator, 9999, numSamples, delayBetweenSamples);

		// Trigger
		tracker.triggerStackTraceSample(jobVertex);

		verify(sampleCoordinator).triggerStackTraceSample(
				eq(taskVertices),
				eq(numSamples),
				eq(delayBetweenSamples),
				eq(BackPressureStatsTracker.MAX_STACK_TRACE_DEPTH));

		// Trigger again for pending request, should not fire
		tracker.triggerStackTraceSample(jobVertex);

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
		samplePromise.success(sample);

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