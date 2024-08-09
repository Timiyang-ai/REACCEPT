@Override
	public void recover() throws Exception {
		LOG.info("Recovering checkpoints from ZooKeeper.");

		// Clear local handles in order to prevent duplicates on
		// recovery. The local handles should reflect the state
		// of ZooKeeper.
		checkpointStateHandles.clear();

		// Get all there is first
		List<Tuple2<RetrievableStateHandle<CompletedCheckpoint>, String>> initialCheckpoints;
		while (true) {
			try {
				initialCheckpoints = checkpointsInZooKeeper.getAllSortedByNameAndLock();
				break;
			}
			catch (ConcurrentModificationException e) {
				LOG.warn("Concurrent modification while reading from ZooKeeper. Retrying.");
			}
		}

		int numberOfInitialCheckpoints = initialCheckpoints.size();

		LOG.info("Found {} checkpoints in ZooKeeper.", numberOfInitialCheckpoints);

		for (Tuple2<RetrievableStateHandle<CompletedCheckpoint>, String> checkpointStateHandle : initialCheckpoints) {

			CompletedCheckpoint completedCheckpoint = null;

			try {
				completedCheckpoint = retrieveCompletedCheckpoint(checkpointStateHandle);
			} catch (Exception e) {
				LOG.warn("Could not retrieve checkpoint. Removing it from the completed " +
					"checkpoint store.", e);

				// remove the checkpoint with broken state handle
				removeBrokenStateHandle(checkpointStateHandle.f1, checkpointStateHandle.f0);
			}

			if (completedCheckpoint != null) {
				completedCheckpoint.registerSharedStates(sharedStateRegistry);
				checkpointStateHandles.add(checkpointStateHandle);
			}
		}
	}