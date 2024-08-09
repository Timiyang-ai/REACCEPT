@Test
	public void testLoadAndValidateSavepoint() throws Exception {
		File tmp = tmpFolder.newFolder();

		int parallelism = 128128;
		long checkpointId = Integer.MAX_VALUE + 123123L;
		JobVertexID jobVertexID = new JobVertexID();
		OperatorID operatorID = OperatorID.fromJobVertexID(jobVertexID);

		OperatorState state = mock(OperatorState.class);
		when(state.getParallelism()).thenReturn(parallelism);
		when(state.getOperatorID()).thenReturn(operatorID);
		when(state.getMaxParallelism()).thenReturn(parallelism);

		Map<OperatorID, OperatorState> taskStates = new HashMap<>();
		taskStates.put(operatorID, state);

		JobID jobId = new JobID();

		// Store savepoint
		SavepointV2 savepoint = new SavepointV2(checkpointId, taskStates.values(), Collections.<MasterState>emptyList());
		String path = SavepointStore.storeSavepoint(tmp.getAbsolutePath(), savepoint);

		ExecutionJobVertex vertex = mock(ExecutionJobVertex.class);
		when(vertex.getParallelism()).thenReturn(parallelism);
		when(vertex.getMaxParallelism()).thenReturn(parallelism);
		when(vertex.getOperatorIDs()).thenReturn(Collections.singletonList(operatorID));

		Map<JobVertexID, ExecutionJobVertex> tasks = new HashMap<>();
		tasks.put(jobVertexID, vertex);

		ClassLoader ucl = Thread.currentThread().getContextClassLoader();

		// 1) Load and validate: everything correct
		CompletedCheckpoint loaded = SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, false);

		assertEquals(jobId, loaded.getJobId());
		assertEquals(checkpointId, loaded.getCheckpointID());

		// 2) Load and validate: max parallelism mismatch
		when(vertex.getMaxParallelism()).thenReturn(222);
		when(vertex.isMaxParallelismConfigured()).thenReturn(true);

		try {
			SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, false);
			fail("Did not throw expected Exception");
		} catch (IllegalStateException expected) {
			assertTrue(expected.getMessage().contains("Max parallelism mismatch"));
		}

		// 3) Load and validate: missing vertex
		assertNotNull(tasks.remove(jobVertexID));

		try {
			SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, false);
			fail("Did not throw expected Exception");
		} catch (IllegalStateException expected) {
			assertTrue(expected.getMessage().contains("allowNonRestoredState"));
		}

		// 4) Load and validate: ignore missing vertex
		SavepointLoader.loadAndValidateSavepoint(jobId, tasks, path, ucl, true);
	}