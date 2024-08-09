@Test
	public void testLoadAndValidateSavepoint() throws Exception {
		int parallelism = 128128;
		JobVertexID vertexId = new JobVertexID();

		TaskState state = mock(TaskState.class);
		when(state.getParallelism()).thenReturn(parallelism);
		when(state.getJobVertexID()).thenReturn(vertexId);

		Map<JobVertexID, TaskState> taskStates = new HashMap<>();
		taskStates.put(vertexId, state);

		CompletedCheckpoint stored = new CompletedCheckpoint(
				new JobID(),
				Integer.MAX_VALUE + 123123L,
				10200202,
				1020292988,
				taskStates,
				true);

		// Store savepoint
		SavepointV0 savepoint = new SavepointV0(stored.getCheckpointID(), taskStates.values());
		SavepointStore store = new HeapSavepointStore();
		String path = store.storeSavepoint(savepoint);

		JobID jobId = new JobID();

		ExecutionJobVertex vertex = mock(ExecutionJobVertex.class);
		when(vertex.getParallelism()).thenReturn(parallelism);

		Map<JobVertexID, ExecutionJobVertex> tasks = new HashMap<>();
		tasks.put(vertexId, vertex);

		// 1) Load and validate: everything correct
		CompletedCheckpoint loaded = SavepointLoader.loadAndValidateSavepoint(jobId, tasks, store, path);

		assertEquals(jobId, loaded.getJobId());
		assertEquals(stored.getCheckpointID(), loaded.getCheckpointID());

		// The loaded checkpoint should not discard state when its discarded
		loaded.discard(ClassLoader.getSystemClassLoader());
		verify(state, times(0)).discard(any(ClassLoader.class));

		// 2) Load and validate: parallelism mismatch
		when(vertex.getParallelism()).thenReturn(222);

		try {
			SavepointLoader.loadAndValidateSavepoint(jobId, tasks, store, path);
			fail("Did not throw expected Exception");
		} catch (IllegalStateException expected) {
			assertTrue(expected.getMessage().contains("Parallelism mismatch"));
		}

		// 3) Load and validate: missing vertex (this should be relaxed)
		assertNotNull(tasks.remove(vertexId));

		try {
			SavepointLoader.loadAndValidateSavepoint(jobId, tasks, store, path);
			fail("Did not throw expected Exception");
		} catch (IllegalStateException expected) {
			assertTrue(expected.getMessage().contains("Cannot map old state"));
		}
	}