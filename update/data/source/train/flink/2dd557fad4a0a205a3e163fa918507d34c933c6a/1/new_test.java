@Test
	public void testGetSnapshottingSettings() throws Exception {
		ExecutionJobVertex jobVertex = mock(ExecutionJobVertex.class);
		when(jobVertex.getJobVertexId()).thenReturn(new JobVertexID());
		when(jobVertex.getParallelism()).thenReturn(1);

		JobCheckpointingSettings snapshottingSettings = new JobCheckpointingSettings(
			Collections.singletonList(new JobVertexID()),
			Collections.singletonList(new JobVertexID()),
			Collections.singletonList(new JobVertexID()),
			new CheckpointCoordinatorConfiguration(
				181238123L,
				19191992L,
				191929L,
				123,
				ExternalizedCheckpointSettings.none(),
				false
			),
			null);

		CheckpointStatsTracker tracker = new CheckpointStatsTracker(
			0,
			Collections.singletonList(jobVertex),
			snapshottingSettings.getCheckpointCoordinatorConfiguration(),
			new UnregisteredMetricsGroup());

		assertEquals(snapshottingSettings.getCheckpointCoordinatorConfiguration(), tracker.getJobCheckpointingConfiguration());
	}