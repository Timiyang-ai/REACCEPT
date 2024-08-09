public static CompletedCheckpoint loadAndValidateSavepoint(
			JobID jobId,
			Map<JobVertexID, ExecutionJobVertex> tasks,
			SavepointStore savepointStore,
			String savepointPath) throws Exception {

		// (1) load the savepoint
		Savepoint savepoint = savepointStore.loadSavepoint(savepointPath);
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
									"Cannot map operator %s with parallelism %d to new program with " +
									"parallelism %d. This indicates that the program has been changed " +
									"in a non-compatible way after the savepoint.",
							savepoint,
							taskState.getJobVertexID(),
							taskState.getParallelism(),
							executionJobVertex.getParallelism());

					throw new IllegalStateException(msg);
				}
			} else {
				String msg = String.format("Failed to rollback to savepoint %s. " +
								"Cannot map old state for task %s to the new program. " +
								"This indicates that the program has been changed in a " +
								"non-compatible way  after the savepoint.",
						savepointPath, taskState.getJobVertexID());
				throw new IllegalStateException(msg);
			}
		}

		// (3) convert to checkpoint so the system can fall back to it
		return new CompletedCheckpoint(jobId, savepoint.getCheckpointId(), 0L, 0L, taskStates, false);
	}