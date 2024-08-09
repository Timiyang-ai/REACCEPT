@Override
	public CompletableFuture<Void> dispose() {

		Collection<Map.Entry<Long, TaskStateSnapshot>> statesCopy;

		synchronized (lock) {
			disposed = true;
			statesCopy = new ArrayList<>(storedTaskStateByCheckpointID.entrySet());
			storedTaskStateByCheckpointID.clear();
		}

		return CompletableFuture.runAsync(
			() -> {
				// discard all remaining state objects.
				syncDiscardLocalStateForCollection(statesCopy);

				// delete the local state subdirectory that belong to this subtask.
				LocalRecoveryDirectoryProvider directoryProvider = localRecoveryConfig.getLocalStateDirectoryProvider();
				for (int i = 0; i < directoryProvider.allocationBaseDirsCount(); ++i) {
					File subtaskBaseDirectory = directoryProvider.selectSubtaskBaseDirectory(i);
					try {
						deleteDirectory(subtaskBaseDirectory);
					} catch (IOException e) {
						LOG.warn("Exception when deleting local recovery subtask base directory {} in subtask ({} - {} - {})",
							subtaskBaseDirectory, jobID, jobVertexID, subtaskIndex, e);
					}
				}
			},
			discardExecutor);
	}