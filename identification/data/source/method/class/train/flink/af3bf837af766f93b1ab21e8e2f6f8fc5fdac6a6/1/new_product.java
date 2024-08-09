public static CompletedCheckpoint loadAndValidateSavepoint(
			JobID jobId,
			Map<JobVertexID, ExecutionJobVertex> tasks,
			String savepointPath,
			ClassLoader userClassLoader,
			boolean allowNonRestoredState) throws IOException {

		// (1) load the savepoint
		Savepoint savepoint = SavepointStore.loadSavepoint(savepointPath, userClassLoader);
		final Map<JobVertexID, TaskState> taskStates = new HashMap<>(savepoint.getTaskStates().size());

		// (2) validate it (parallelism, etc)
		for (TaskState taskState : savepoint.getTaskStates()) {
			ExecutionJobVertex executionJobVertex = tasks.get(taskState.getJobVertexID());

			if (executionJobVertex != null) {
				if (executionJobVertex.getMaxParallelism() == taskState.getMaxParallelism()) {
					taskStates.put(taskState.getJobVertexID(), taskState);
				}
				else {
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
		return new CompletedCheckpoint(jobId, savepoint.getCheckpointId(), 0L, 0L, taskStates, props, savepointPath);
	}