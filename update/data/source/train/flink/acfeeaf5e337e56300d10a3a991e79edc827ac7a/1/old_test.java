@Test
	public void testLoadAndValidateSavepoint() throws Exception {
		File tmp = tmpFolder.newFolder();

		int parallelism = 128128;
		long checkpointId = Integer.MAX_VALUE + 123123L;
		JobVertexID vertexId = new JobVertexID();

		TaskState state = mock(TaskState.class);
		when(state.getParallelism()).thenReturn(parallelism);
		when(state.getJobVertexID()).thenReturn(vertexId);
		when(state.getMaxParallelism()).thenReturn(parallelism);
		when(state.getChainLength()).thenReturn(1);

		Map<JobVertexID, TaskState> taskStates = new HashMap<>();
		taskStates.put(vertexId, state);

		// Store savepoint
		SavepointV1 savepoint = new SavepointV1(checkpointId, taskStates.values());
		String path = SavepointStore.storeSavepoint(tmp.getAbsolutePath(), savepoint);

		JobID jobId = new JobID();

		ExecutionJobVertex vertex = mock(ExecutionJobVertex.class);
		when(vertex.getParallelism()).thenReturn(parallelism);
		when(vertex.getMaxParallelism()).thenReturn(parallelism);

		Map<JobVertexID, ExecutionJobVertex> tasks = new HashMap<>();
		tasks.put(vertexId, vertex);

		ClassLoader ucl = Thread.currentThread().getContextClassLoader();

		// 1) Load and validate: everything correct
		CompletedCheckpoint loaded = SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, false);

		assertEquals(jobId, loaded.getJobId());
		assertEquals(checkpointId, loaded.getCheckpointID());

		// 2) Load and validate: max parallelism mismatch
		when(vertex.getMaxParallelism()).thenReturn(222);

		try {
			SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, false);
			fail("Did not throw expected Exception");
		} catch (IllegalStateException expected) {
			assertTrue(expected.getMessage().contains("Max parallelism mismatch"));
		}

		// 3) Load and validate: missing vertex
		assertNotNull(tasks.remove(vertexId));

		try {
			SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, false);
			fail("Did not throw expected Exception");
		} catch (IllegalStateException expected) {
			assertTrue(expected.getMessage().contains("allowNonRestoredState"));
		}

		// 4) Load and validate: ignore missing vertex
		SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, true);
	}