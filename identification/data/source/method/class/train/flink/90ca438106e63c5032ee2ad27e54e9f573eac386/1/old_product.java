public static CompletedCheckpoint loadAndValidateSavepoint(
			JobID jobId,
			Map<JobVertexID, ExecutionJobVertex> tasks,
			String savepointPath,
			ClassLoader classLoader,
			boolean allowNonRestoredState) throws IOException {

		// (1) load the savepoint
		final Tuple2<Savepoint, StreamStateHandle> savepointAndHandle = 
				SavepointStore.loadSavepointWithHandle(savepointPath, classLoader);

		final Savepoint savepoint = savepointAndHandle.f0;
		final StreamStateHandle metadataHandle = savepointAndHandle.f1;

		final Map<JobVertexID, TaskState> taskStates = new HashMap<>(savepoint.getTaskStates().size());

		boolean expandedToLegacyIds = false;

		// (2) validate it (parallelism, etc)
		for (TaskState taskState : savepoint.getTaskStates()) {

			ExecutionJobVertex executionJobVertex = tasks.get(taskState.getJobVertexID());

			// on the first time we can not find the execution job vertex for an id, we also consider alternative ids,
			// for example as generated from older flink versions, to provide backwards compatibility.
			if (executionJobVertex == null && !expandedToLegacyIds) {
				tasks = ExecutionJobVertex.includeLegacyJobVertexIDs(tasks);
				executionJobVertex = tasks.get(taskState.getJobVertexID());
				expandedToLegacyIds = true;
				LOG.info("Could not find ExecutionJobVertex. Including legacy JobVertexIDs in search.");
			}

			if (executionJobVertex != null) {

				if (executionJobVertex.getMaxParallelism() == taskState.getMaxParallelism()
						|| !executionJobVertex.isMaxParallelismConfigured()) {
					taskStates.put(taskState.getJobVertexID(), taskState);
				} else {
					String msg = String.format("Failed to rollback to savepoint %s. " +
									"Max parallelism mismatch between savepoint state and new program. " +
									"Cannot map operator %s with max parallelism %d to new program with " +
									"max parallelism %d. This indicates that the program has been changed " +
									"in a non-compatible way after the savepoint.",
							savepoint,
							taskState.getJobVertexID(),
							taskState.getMaxParallelism(),
							executionJobVertex.getMaxParallelism());

					throw new IllegalStateException(msg);
				}
			} else if (allowNonRestoredState) {
				LOG.info("Skipping savepoint state for operator {}.", taskState.getJobVertexID());
			} else {
				String msg = String.format("Failed to rollback to savepoint %s. " +
								"Cannot map savepoint state for operator %s to the new program, " +
								"because the operator is not available in the new program. If " +
								"you want to allow to skip this, you can set the --allowNonRestoredState " +
								"option on the CLI.",
						savepointPath, taskState.getJobVertexID());

				throw new IllegalStateException(msg);
			}
		}

		// (3) convert to checkpoint so the system can fall back to it
		CheckpointProperties props = CheckpointProperties.forStandardSavepoint();
		return new CompletedCheckpoint(jobId, savepoint.getCheckpointId(), 0L, 0L,
				taskStates, props, metadataHandle, savepointPath);
	}