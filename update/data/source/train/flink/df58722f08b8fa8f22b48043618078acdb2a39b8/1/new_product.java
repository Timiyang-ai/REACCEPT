public static CompletedCheckpoint loadAndValidateSavepoint(
			JobID jobId,
			Map<JobVertexID, ExecutionJobVertex> tasks,
			String savepointPath,
			ClassLoader classLoader,
			boolean allowNonRestoredState) throws IOException {

		// (1) load the savepoint
		final Tuple2<Savepoint, StreamStateHandle> savepointAndHandle = 
				SavepointStore.loadSavepointWithHandle(savepointPath, classLoader);

		Savepoint savepoint = savepointAndHandle.f0;
		final StreamStateHandle metadataHandle = savepointAndHandle.f1;

		if (savepoint.getTaskStates() != null) {
			savepoint = SavepointV2.convertToOperatorStateSavepointV2(tasks, savepoint);
		}
		// generate mapping from operator to task
		Map<OperatorID, ExecutionJobVertex> operatorToJobVertexMapping = new HashMap<>();
		for (ExecutionJobVertex task : tasks.values()) {
			for (OperatorID operatorID : task.getOperatorIDs()) {
				operatorToJobVertexMapping.put(operatorID, task);
			}
		}

		// (2) validate it (parallelism, etc)
		boolean expandedToLegacyIds = false;

		HashMap<OperatorID, OperatorState> operatorStates = new HashMap<>(savepoint.getOperatorStates().size());
		for (OperatorState operatorState : savepoint.getOperatorStates()) {

			ExecutionJobVertex executionJobVertex = operatorToJobVertexMapping.get(operatorState.getOperatorID());

			// on the first time we can not find the execution job vertex for an id, we also consider alternative ids,
			// for example as generated from older flink versions, to provide backwards compatibility.
			if (executionJobVertex == null && !expandedToLegacyIds) {
				operatorToJobVertexMapping = ExecutionJobVertex.includeAlternativeOperatorIDs(operatorToJobVertexMapping);
				executionJobVertex = operatorToJobVertexMapping.get(operatorState.getOperatorID());
				expandedToLegacyIds = true;
				LOG.info("Could not find ExecutionJobVertex. Including user-defined OperatorIDs in search.");
			}

			if (executionJobVertex != null) {

				if (executionJobVertex.getMaxParallelism() == operatorState.getMaxParallelism()
						|| !executionJobVertex.isMaxParallelismConfigured()) {
					operatorStates.put(operatorState.getOperatorID(), operatorState);
				} else {
					String msg = String.format("Failed to rollback to savepoint %s. " +
									"Max parallelism mismatch between savepoint state and new program. " +
									"Cannot map operator %s with max parallelism %d to new program with " +
									"max parallelism %d. This indicates that the program has been changed " +
									"in a non-compatible way after the savepoint.",
							savepoint,
							operatorState.getOperatorID(),
							operatorState.getMaxParallelism(),
							executionJobVertex.getMaxParallelism());

					throw new IllegalStateException(msg);
				}
			} else if (allowNonRestoredState) {
				LOG.info("Skipping savepoint state for operator {}.", operatorState.getOperatorID());
			} else {
				for (OperatorSubtaskState operatorSubtaskState : operatorState.getStates()) {
					if (operatorSubtaskState.hasState()) {
						String msg = String.format("Failed to rollback to savepoint %s. " +
								"Cannot map savepoint state for operator %s to the new program, " +
								"because the operator is not available in the new program. If " +
								"you want to allow to skip this, you can set the --allowNonRestoredState " +
								"option on the CLI.",
							savepointPath, operatorState.getOperatorID());

						throw new IllegalStateException(msg);
					}
				}
				LOG.info("Skipping empty savepoint state for operator {}.", operatorState.getOperatorID());
			}
		}

		// (3) convert to checkpoint so the system can fall back to it
		CheckpointProperties props = CheckpointProperties.forStandardSavepoint();

		return new CompletedCheckpoint(
			jobId,
			savepoint.getCheckpointId(),
			0L,
			0L,
			operatorStates,
			savepoint.getMasterStates(),
			props,
			metadataHandle,
			savepointPath);
	}