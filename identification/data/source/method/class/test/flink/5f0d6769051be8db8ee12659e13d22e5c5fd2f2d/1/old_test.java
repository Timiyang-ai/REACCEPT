@Test
	public void testGetSnapshottingSettings() throws Exception {
		ExecutionJobVertex jobVertex = mock(ExecutionJobVertex.class);
		when(jobVertex.getJobVertexId()).thenReturn(new JobVertexID());
		when(jobVertex.getParallelism()).thenReturn(1);

		JobSnapshottingSettings snapshottingSettings = new JobSnapshottingSettings(
			Collections.singletonList(new JobVertexID()),
			Collections.singletonList(new JobVertexID()),
			Collections.singletonList(new JobVertexID()),
			181238123L,
			19191992L,
			191929L,
			123,
			ExternalizedCheckpointSettings.none(),
			null,
			false);

		CheckpointStatsTracker tracker = new CheckpointStatsTracker(
			0,
			Collections.singletonList(jobVertex),
			snapshottingSettings,
			new UnregisteredMetricsGroup());

		assertEquals(snapshottingSettings, tracker.getSnapshottingSettings());
	}